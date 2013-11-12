/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.common.base.Objects;

/**
 * TODO Comment on type 
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Entity(value="room", noClassnameStored=true)
public class RoomEntity {

	/** The unique object identifier. */
	private @Id ObjectId id;

	/** The name of the room. */
	private String name;
	
	/** The capacity of the room. */
	private Integer capacity;
	
	public ObjectId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * Sets the objectId.
	 *
	 * @param objectId the objectId to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
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
	  		getId(),
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
  	if (obj instanceof RoomEntity) return false;
  	RoomEntity that = (RoomEntity) obj;
	  return (
	  		Objects.equal(this.getId(), that.getId()) &&
	  		Objects.equal(this.getName(), that.getName()) &&
	  		Objects.equal(this.getCapacity(), that.getCapacity())
	  );
  }
}
