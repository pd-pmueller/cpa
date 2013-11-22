/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl.management;

import java.lang.management.ManagementFactory;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.management.ObjectName;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.prodyna.pmu.cpa.ejb.management.MonitoringMXBean;

/**
 * Thread-safe singleton EJB implementation of the {@link MonitoringMXBean}.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Startup @Singleton
public class MonitoringMXBeanImpl implements MonitoringMXBean {

	/**
	 * Atomic implementation of the InvocationStatistics interface.
	 *
	 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
	 */
	public static class InvocationStatisticsImpl implements InvocationStatistics {

		/** The {@code serialVersionUID}. */
    private static final long serialVersionUID = -997055741613021092L;

		/** The atomic minimum time. */
		private AtomicLong timeMin = new AtomicLong(Long.MAX_VALUE);

		/** The atomic maximum time. */
		private AtomicLong timeMax = new AtomicLong(Long.MIN_VALUE);

		/** The atomic total time. */
		private AtomicLong timeTotal = new AtomicLong(0);
		
		/** The atomic invocation count. */
		private AtomicInteger invocationCount = new AtomicInteger(0);
		
		/** The atomic exception count. */
		private AtomicInteger exceptionCount = new AtomicInteger(0);
		
		/**
		 * {@inheritDoc}
		 */
		@Override
    public Long getTimeMin() {
	    return timeMin.get();
    }

		/**
		 * {@inheritDoc}
		 */
		@Override
    public Long getTimeMax() {
	    return timeMax.get();
    }

		/**
		 * {@inheritDoc}
		 */
		@Override
    public Long getTimeAverage() {
	    return (timeTotal.get() / invocationCount.get());
    }

		/**
		 * {@inheritDoc}
		 */
		@Override
    public Long getTimeTotal() {
	    return timeTotal.get();
    }

		/**
		 * {@inheritDoc}
		 */
		@Override
    public Integer getInvocationCount() {
	    return invocationCount.get();
    }

		/**
		 * {@inheritDoc}
		 */
		@Override
    public Integer getExceptionCount() {
	    return exceptionCount.get();
    }

		/**
		 * {@inheritDoc}
		 */
		@Override
    public void reset() {
	    timeMin.set(Long.MAX_VALUE);
	    timeMax.set(Long.MIN_VALUE);
	    timeTotal.set(0);
	    invocationCount.set(0);
	    exceptionCount.set(0);
    }
		
		/**
		 * Updates the statistics.
		 *
		 * @param time The time with which to update.
		 * @param exception Whether an exception occured.
		 */
		protected void update(long time, boolean exception) {
			invocationCount.incrementAndGet();
			if (exception)
				exceptionCount.incrementAndGet();
			// Time
			timeTotal.addAndGet(time);
			if (time < timeMin.get())
				timeMin.set(time);
			if (time > timeMax.get())
				timeMax.set(time);
		}
	}
	
	/** The invocation statistics map. */
	private Map<String, InvocationStatisticsImpl> invocationStatistics = new MapMaker()
		.makeMap();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, InvocationStatistics> getInvocationStatistics() {
		return ImmutableMap.copyOf(
				Maps.transformValues(
						invocationStatistics,
						new Function<InvocationStatisticsImpl, InvocationStatistics>() {
							@Override public InvocationStatistics apply(InvocationStatisticsImpl input) {
	              return input;
              }
						}
				)
		);
	}
	
	@Asynchronous
	protected void onMonitoredInvocation(@Observes MonitoredInvocation event) {
		notify(event.getClassName(), event.getMethodName(), event.getTime(), event.isExceptionThrown());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
  public void notify(String className, String methodName, long time, boolean exceptionThrown) {
		String key = className + "#" + methodName;
		// Initialize
		InvocationStatisticsImpl stats = invocationStatistics.get(key);
		if (stats == null) {
			stats = new InvocationStatisticsImpl();
			invocationStatistics.put(key, stats);
		}
		// Update
		stats.update(time, exceptionThrown);
	}
	
	/**
	 * Registers this bean in JMX.
	 */
  @PostConstruct
  private void register() {
    try {
      ObjectName objectName = new ObjectName(OBJECT_NAME);
      ManagementFactory.getPlatformMBeanServer()
      		.registerMBean(this, objectName);
    }
    catch (Exception e) {
      throw new IllegalStateException("Problem during registration: " + e);
    }
  }
}
