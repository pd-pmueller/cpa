/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.domain;

import java.io.Serializable;

import com.google.common.base.Objects;

/**
 * Serializable class that describes a single room.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class Room implements HasObjectId, HasName, Serializable {

	/** The {@code serialVersionUID}. */
  private static final long serialVersionUID = -6725037517737486160L;

	/** The unique object identifier. */
	private String objectId;

	/** The name of the room. */
	private String name;
	
	/** The capacity of the room. */
	private Integer capacity;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getObjectId() {
		return objectId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the maximum capacity of this room.
	 *
	 * @return the capacity as number of persons.
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * Sets the unique object identifier for this object.
	 *
	 * @param objectId The identifier to set.
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * Sets the name for this object.
	 *
	 * @param name the name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the capacity for this room.
	 *
	 * @param capacity the capacity to set.
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	/**
	 * {@inheritDoc}
	 */
  @Override
  public int hashCode() {
	  return Objects.hashCode(
	  		getObjectId(),
	  		getName(),
	  		getCapacity()
	  );
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public boolean equals(Object obj) {
  	if (obj == null) return false;
  	if (this == obj) return true;
  	if (obj instanceof Room) return false;
  	Room that = (Room) obj;
	  return (
	  		Objects.equal(this.getObjectId(), that.getObjectId()) &&
	  		Objects.equal(this.getName(), that.getName()) &&
	  		Objects.equal(this.getCapacity(), that.getCapacity())
	  );
  }
}
