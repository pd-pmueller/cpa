/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.common.base.Objects;
import com.prodyna.pmu.cpa.common.entity.Room;

/**
 * TODO Comment on type 
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Entity(value="room", noClassnameStored=true)
public class RoomImpl implements Room {

	/** The unique object identifier. */
	@Id
	private String id;

	/** The name of the room. */
	private String name;
	
	/** The capacity of the room. */
	private Integer capacity;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getObjectId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * Sets the objectId.
	 *
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.id = objectId;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity the capacity to set
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
