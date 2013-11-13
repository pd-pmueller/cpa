/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa;

import java.io.Serializable;

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

	/** The talk's time limit in minutes. */
	private Integer timeLimit;
	
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
	 * @param timeLimit The time limit to set.
	 */
  public Talk(String objectId, String name, String description, Integer timeLimit) {
    this();
  	this.objectId = objectId;
    this.name = name;
    this.description = description;
    this.timeLimit = timeLimit;
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
	 * Returns the time limit for this talk, in minutes.
	 *
	 * @return a number of minutes.
	 */
	public Integer getTimeLimit() {
		return timeLimit;
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
	 * Sets the time limit for this talk.
	 *
	 * @param timeLimit The time limit to set.
	 */
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
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
  	if (obj instanceof Talk) return false;
  	Talk that = (Talk) obj;
	  return (
	  		Objects.equal(this.getObjectId(), that.getObjectId()) &&
	  		Objects.equal(this.getName(), that.getName()) &&
	  		Objects.equal(this.getDescription(), that.getDescription()) &&
	  		Objects.equal(this.getTimeLimit(), that.getTimeLimit())
	  );
  }
}
