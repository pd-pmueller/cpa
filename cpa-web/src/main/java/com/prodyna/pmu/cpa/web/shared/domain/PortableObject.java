/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.shared.domain;

import java.io.Serializable;

/**
 * Interface for a portable object.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public interface PortableObject extends Serializable {

	/**
	 * Returns the unique object identifier for this object.
	 *
	 * @return a unique object identifier.
	 */
	String getObjectId();
	
	/**
	 * Returns the name for this object.
	 *
	 * @return a simple name.
	 */
	String getName();
}
