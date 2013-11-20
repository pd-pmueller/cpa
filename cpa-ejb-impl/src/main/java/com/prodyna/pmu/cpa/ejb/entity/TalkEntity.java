/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.common.base.Objects;
import com.prodyna.pmu.cpa.domain.Talk;

/**
 * {@code Morphia}/{@code MongoDB}-specific implementation of the {@link Talk} entity.
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
	
	/** The scheduled time. */
	private Date time;
	
	/** The duration in minutes. */
	private Integer duration;

	/** The speakers assigned to this talk. */
	private Set<ObjectId> speakers = new LinkedHashSet<ObjectId>();
	
	public ObjectId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public Date getTime() {
		return time;
	}

	public Integer getDuration() {
		return duration;
	}

	public Set<ObjectId> getSpeakers() {
		return speakers;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setDuration(Integer timeLimit) {
		this.duration = timeLimit;
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
	  		getTime(),
	  		getDuration(),
	  		getSpeakers()
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
	  		Objects.equal(this.getTime(), that.getTime()) &&
	  		Objects.equal(this.getDuration(), that.getDuration()) &&
	  		Objects.equal(this.getSpeakers(), that.getSpeakers())
	  );
  }
}
