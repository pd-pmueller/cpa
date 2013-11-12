/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.common.entity;

/**
 * Minimal interface for 'room' entities.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public interface Room extends BaseEntity {

	/**
	 * Returns the unique name of this room.
	 *
	 * @return a room name.
	 */
	String getName();
	
	/**
	 * Returns the maximum capacity of this room.
	 *
	 * @return the capacity as number of persons.
	 */
	Integer getCapacity();
}
