/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.common.base.Objects;

/**
 * Serializable class that describes a single talk.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class Talk implements HasObjectId, HasName, Serializable {

	/** The {@code serialVersionUID}. */
  private static final long serialVersionUID = -6211271286655017993L;

	/** The unique object identifier. */
	private String objectId;
	
	/** The name of the speaker. */
	private String name;
	
	/** A brief description of the speaker. */
	private String description;

	/** The time the talk is scheduled. */
	private Date time;
	
	/** The talk's time limit in minutes. */
	private Integer duration;
	
	/** The speakers assigned to this talk. */
	private Set<String> speakers = new LinkedHashSet<String>();
	
	/**
	 * Constructs a new, empty {@code Talk} object.
	 */
	public Talk() {
		super();
	}
	
	/**
	 * Constructs a new {@code Talk} object.
	 *
	 * @param objectId The unique object identifier to set.
	 * @param name The name to set.
	 * @param description The description to set.
	 * @param time The time to schedule the talk for.
	 * @param duration The duration to set.
	 */
  public Talk(String objectId, String name, String description, Date time, Integer duration) {
    this();
  	this.objectId = objectId;
    this.name = name;
    this.description = description;
    this.time = time;
    this.duration = duration;
  }

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
	 * Returns a brief description for this talk.
	 *
	 * @return a description text.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the time this talk is scheduled at.
	 *
	 * @return the scheduled time for this talk.
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Returns the time limit for this talk, in minutes.
	 *
	 * @return a number of minutes.
	 */
	public Integer getDuration() {
		return duration;
	}
	
	/**
	 * Returns the speakers assigned to this talk.
	 *
	 * @return a set containing the {@code Speaker} identifiers.
	 */
	public Set<String> getSpeakers() {
		return speakers;
	}

	/**
	 * Sets the unique object identifier for this talk.
	 *
	 * @param objectId the identifier to set.
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * Sets the name for this talk.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the description for this talk.
	 *
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the time this talk is scheduled at.
	 *
	 * @param time The time to set.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * Sets the duration of this talk.
	 *
	 * @param duration The duration to set.
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * {@inheritDoc}
	 */
  @Override
  public int hashCode() {
	  return Objects.hashCode(
	  		getObjectId(),
	  		getName(),
	  		getDescription(),
	  		getTime(),
	  		getDuration()
	  );
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public boolean equals(Object obj) {
  	if (obj == null) return false;
  	if (this == obj) return true;
  	if (obj instanceof Talk) return false;
  	Talk that = (Talk) obj;
	  return (
	  		Objects.equal(this.getObjectId(), that.getObjectId()) &&
	  		Objects.equal(this.getName(), that.getName()) &&
	  		Objects.equal(this.getDescription(), that.getDescription()) &&
	  		Objects.equal(this.getTime(), that.getTime()) &&
	  		Objects.equal(this.getDuration(), that.getDuration())
	  );
  }
}
