/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl.management;

/**
 * Event to be raised whenever a {@code @Monitored} method is called.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class MonitoredInvocation {

	/** The name of the class containing the method that was called. */
	private String className;
	/** The name of the method that was called. */
	private String methodName;
	/** The method's runtime. */
	private long time;
	/** Whether an exception was thrown. */
	private boolean exceptionThrown;
	
	/**
	 * Constructs a new {@code MonitoredInvocation} object.
	 *
	 * @param className The name of the class containing the method that was called.
	 * @param methodName The name of the method that was called.
	 * @param time The method's runtime.
	 * @param exceptionThrown Whether an exception was thrown.
	 */
  public MonitoredInvocation(String className, String methodName, long time, boolean exceptionThrown) {
	  this.className = className;
	  this.methodName = methodName;
	  this.time = time;
	  this.exceptionThrown = exceptionThrown;
  }
  
  /**
   * Returns the name of the class containing the method that was called.
   *
   * @return the class name.
   */
	public String getClassName() {
		return className;
	}
	
	/**
	 * Returns the name of the method that was called.
	 *
	 * @return the method name.
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * Returns the measured runtime of the method.
	 *
	 * @return the runtime in milliseconds.
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * Returns whether an exception was thrown by the method.
	 *
	 * @return {@code true} if an exception was thrown; {@code false} otherwise.
	 */
	public boolean isExceptionThrown() {
		return exceptionThrown;
	}
}
