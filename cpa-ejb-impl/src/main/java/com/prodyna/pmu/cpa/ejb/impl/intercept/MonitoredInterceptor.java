/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl.intercept;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.prodyna.pmu.cpa.ejb.impl.management.MonitoredInvocation;
import com.prodyna.pmu.cpa.ejb.intercept.Monitored;
import com.prodyna.pmu.cpa.ejb.management.MonitoringMXBean;

/**
 * Interceptor that raises a {@link MonitoredInvocation} event whenever a monitored method is called.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Monitored @Interceptor
public class MonitoredInterceptor {
	
	/** The event to raise. */
	@Inject
	private Event<MonitoredInvocation> event;
	
	/**
	 * Intercepts method calls and notifies the {@link MonitoringMXBean} of the invocation.
	 * <p>
	 * This is only done for {@code public} methods or methods with the {@link Monitored} annotation.
	 *
	 * @param context The invocation context.
	 * @return the original return value of the method call.
	 * @throws Exception if an error occurs processing the method call.
	 */
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		if (shouldIntercept(context)) {
  		Stopwatch stopwatch = Stopwatch.createStarted();
  		boolean exception = false;
  		try {
  			return context.proceed();
  		}
  		catch (Throwable t) {
  			exception = true;
  			Throwables.propagateIfInstanceOf(t, Exception.class);
  			throw Throwables.propagate(t);
  		}
  		finally {
  			stopwatch.stop();
  			event.fire(new MonitoredInvocation(
  					context.getMethod().getDeclaringClass().getName(),
  					context.getMethod().getName(),
  					stopwatch.elapsed(TimeUnit.MILLISECONDS), 
  					exception
  			));
  		}
		}
		else {
			return context.proceed();
		}
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
			Modifier.isPublic(method.getModifiers()) ||
    	method.isAnnotationPresent(Monitored.class)
		);
	}
}
