/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.domain;

/**
 * Interface for an object that holds a unique object identifier.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public interface HasObjectId {

	/**
	 * Returns the unique object key for this object.
	 *
	 * @return a unique object key.
	 */
	String getObjectId();
}
