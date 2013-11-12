/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.common.entity;

/**
 * Base interface for entities.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public interface BaseEntity {

	/**
	 * Returns the unique object identifier for this speaker.
	 *
	 * @return a unique object identifier.
	 */
	String getObjectId();
}
