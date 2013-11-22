/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.cdi;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.naming.InitialContext;

import com.google.common.base.Throwables;

/**
 * Producer for {@code javax.naming}-related objects.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class NamingProducer {

	/**
	 * Creates and returns an {@link InitialContext} object.
	 *
	 * @return an initial context.
	 */
	@Produces @Singleton
	public InitialContext getInitialContext() {
		try {
			return new InitialContext();
		}
		catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}
}
