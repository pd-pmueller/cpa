/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.common.entity;

/**
 * Minimal interface for 'speaker' entities.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public interface Speaker extends BaseEntity {

	/**
	 * Returns the full name of this speaker.
	 *
	 * @return a name.
	 */
	String getName();
	
	/**
	 * Returns a brief description of this speaker.
	 *
	 * @return a description text.
	 */
	String getDescription();
}
