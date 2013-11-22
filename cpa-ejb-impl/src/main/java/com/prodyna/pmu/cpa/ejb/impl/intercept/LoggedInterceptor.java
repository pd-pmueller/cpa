/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl.intercept;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

import com.google.common.base.Joiner;
import com.google.common.base.Stopwatch;
import com.prodyna.pmu.cpa.ejb.intercept.Logged;

/**
 * Interceptor that logs all {@code public} service bean methods and their performance.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Logged @Interceptor
public class LoggedInterceptor {
	
	/** The message format to use. */
	private static final MessageFormat MESSAGE = new MessageFormat("{0}#{1}({2}): {3}");

	/** The logger instance to use. */
	@Inject
	private Logger logger;
	
	/**
	 * Intercepts method calls and logs the following information as {@code TRACE} message:
	 * <ul>
	 * 	<li>class,</li>
	 * 	<li>method,</li>
	 * 	<li>parameters, and</li>
	 * 	<li>processing time</li>
	 * </ul>
	 * This is only done for {@code public} methods or methods with the {@link Logged} annotation.
	 *
	 * @param context The invocation context.
	 * @return the original return value of the method call.
	 * @throws Exception if an error occurs processing the method call.
	 */
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		if (shouldIntercept(context)) {
  		Stopwatch stopwatch = Stopwatch.createStarted();
  		try {
  			return context.proceed();
  		}
  		finally {
  			stopwatch.stop();
  			log(context, stopwatch);
  		}
		}
		else {
			return context.proceed();
		}
	}
	
	/**
	 * Logs the specified invocation and time measurement.
	 *
	 * @param context The invocation context.
	 * @param stopwatch The stopwatch used to measure processing time.
	 */
	private void log(InvocationContext context, Stopwatch stopwatch) {
		Method method = context.getMethod();
		Object[] parameters = context.getParameters();
		// Log
		logger.trace(MESSAGE.format(new Object[] {
				method.getDeclaringClass().getName(),
				method.getName(),
				Joiner.on(',').join(parameters),
				stopwatch.elapsed(TimeUnit.MILLISECONDS)
		}));
	}
	
	/**
	 * Checks and returns if the specified method call should be intercepted.
	 *
	 * @param context The context to check.
	 * @return {@code true} if the call should be intercepted; {@code false} otherwise.
	 */
	private boolean shouldIntercept(InvocationContext context) {
		Method method = context.getMethod();
		return (
				logger.isTraceEnabled() &&
				(
						Modifier.isPublic(method.getModifiers()) ||
						method.isAnnotationPresent(Logged.class)
				)
		);
	}
}
