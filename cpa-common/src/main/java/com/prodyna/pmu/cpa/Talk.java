/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa;

/**
 * Minimal interface for 'talk' entities.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public interface Talk extends HasObjectId, HasName {

	/**
	 * Returns a brief description of this talk.
	 *
	 * @return a description text.
	 */
	String getDescription();
	
	/**
	 * Returns the time limit for this talk, in minutes.
	 *
	 * @return a number of minutes.
	 */
	Integer getTimeLimit();
}
