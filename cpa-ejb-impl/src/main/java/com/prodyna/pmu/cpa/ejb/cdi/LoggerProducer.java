/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.cdi;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Producer for {@code Logger}-related objects.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class LoggerProducer {

	/**
	 * Produces and returns an SLF4J {@code Logger} instance.
	 *
	 * @param injectionPoint The injection point.
	 * @return a {@code Logger} instance.
	 */
	@Produces 
  public Logger getLogger(final InjectionPoint injectionPoint) { 
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass()); 
  }
}
