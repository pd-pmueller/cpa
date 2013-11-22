/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.management;

import java.io.Serializable;
import java.util.Map;

import javax.management.MXBean;

/**
 * {@code MXBean} that holds monitoring information.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public @MXBean interface MonitoringMXBean {

	/**
	 * Statistics for a single method.
	 */
	public static interface InvocationStatistics extends Serializable {
		
		/**
		 * Returns the minimum runtime of the method.
		 *
		 * @return the time in milliseconds.
		 */
		Long getTimeMin();
		
		/**
		 * Returns the maximum runtime of the method.
		 *
		 * @return the time in milliseconds.
		 */
		Long getTimeMax();
		
		/**
		 * Returns the average runtime of the method.
		 *
		 * @return the time in milliseconds.
		 */
		Long getTimeAverage();
		
		/**
		 * Returns the total runtime of the method, across all invocations.
		 *
		 * @return the time in milliseconds.
		 */
		Long getTimeTotal();
		
		/**
		 * Returns the number of times the method was invoked.
		 *
		 * @return the number of invocations.
		 */
		Integer getInvocationCount();
		
		/**
		 * Returns the number of times the method was invoked and resulted in an exception being raised.
		 *
		 * @return the number of exceptions thrown.
		 */
		Integer getExceptionCount();
		
		/**
		 * Resets the statistics for this method.
		 */
		void reset();
	}

	/** The object name to use. */
	public static final String OBJECT_NAME = "com.prodyna.pmu.cpa:type=Monitor";
	
	/**
	 * Returns statistics for monitored methods, using the class and method name as key.
	 *
	 * @return the invocation statistics for all monitored methods.
	 */
	Map<String, InvocationStatistics> getInvocationStatistics();
	
	/**
	 * Notifies this monitoring bean of a method invocation.
	 *
	 * @param className The name of the method's class.
	 * @param methodName The name of the method.
	 * @param time The runtime of the method call.
	 * @param exceptionThrown Whether an exception was thrown.
	 */
	void notify(String className, String methodName, long time, boolean exceptionThrown);
}
