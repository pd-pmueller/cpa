/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.common.base.Objects;
import com.prodyna.pmu.cpa.domain.Room;
import com.prodyna.pmu.cpa.ejb.domain.validation.group.Create;
import com.prodyna.pmu.cpa.ejb.domain.validation.group.Update;

/**
 * {@code Morphia}/{@code MongoDB}-specific implementation of the {@link Room} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Entity(value="room", noClassnameStored=true)
public class RoomEntity {

	/** The implementation-specific unique object identifier. */
	@Null(groups=Create.class) @NotNull(groups=Update.class)
	private @Id ObjectId id;

	/** The name of the room. */
	@NotNull
	private String name;
	
	/** The capacity of the room. */
	@Min(0) @Max(999)
	private Integer capacity;
	
	/**
	 * Returns the implementation-specific unique object identifier.
	 *
	 * @return the object identifier.
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * Returns the name of this room.
	 *
	 * @return a room name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the capacity of this room, in number of persons.
	 *
	 * @return the capacity.
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * Sets the implementation-specific unique object identifier.
	 *
	 * @param id The identifier to set.
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

	/**
	 * Sets the name of this room.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the capacity of this room, in number of persons.
	 *
	 * @param capacity The capacity to set.
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
