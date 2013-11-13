/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.common.base.Objects;
import com.prodyna.pmu.cpa.Room;
import com.prodyna.pmu.cpa.Talk;

/**
 * {@code Morphia}/{@code MongoDB}-specific implementation of the {@link Room} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Entity(value="talk", noClassnameStored=true)
public class TalkEntity {

	/** The unique object identifier. */
	private @Id ObjectId id;

	/** The name of the talk. */
	private String name;

	/** A brief description of the talk. */
	private String description;
	
	/** The time limit in minutes. */
	private Integer timeLimit;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * {@inheritDoc}
	 */
  @Override
  public int hashCode() {
	  return Objects.hashCode(
	  		getId(),
	  		getName(),
	  		getDescription(),
	  		getTimeLimit()
	  );
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public boolean equals(Object obj) {
  	if (obj == null) return false;
  	if (this == obj) return true;
  	if (obj instanceof TalkEntity) return false;
  	TalkEntity that = (TalkEntity) obj;
	  return (
	  		Objects.equal(this.getId(), that.getId()) &&
	  		Objects.equal(this.getName(), that.getName()) &&
	  		Objects.equal(this.getDescription(), that.getDescription()) &&
	  		Objects.equal(this.getTimeLimit(), that.getTimeLimit())
	  );
  }
}
