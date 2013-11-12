/*
 * $Id$
 * Copyright 2013 PRODYNA AG 
 */
package com.prodyna.pmu.cpa.common.entity;

import java.util.Date;

/**
 * Minimal interface for 'conference' entities.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public interface Conference extends BaseEntity {

	/**
	 * Returns the name of this conference.
	 *
	 * @return a conference name.
	 */
	String getName();
	
	/**
	 * Returns a brief description for this conference.
	 *
	 * @return a description text.
	 */
	String getDescription();
	
	/**
	 * Returns the begin date of this conference.
	 *
	 * @return the day the conference begins.
	 */
	Date getBeginDate();
	
	/**
	 * Returns the end date of this conference.
	 *
	 * @return the day the conference ends.
	 */
	Date getEndDate();
}
