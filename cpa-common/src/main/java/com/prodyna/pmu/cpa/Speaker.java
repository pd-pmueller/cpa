/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa;

import java.io.Serializable;

import com.google.common.base.Objects;

/**
 * Serializable class that describes a single speaker.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class Speaker implements HasObjectId, HasName, Serializable {

	/** The {@code serialVersionUID}. */
  private static final long serialVersionUID = -1973712869193247386L;

	/** The unique object identifier. */
	private String objectId;
	
	/** The name of the speaker. */
	private String name;
	
	/** A brief description of the speaker. */
	private String description;

	/**
	 * Constructs a new, empty {@code Speaker} object.
	 */
	public Speaker() {
		super();
	}
	
	/**
	 * Constructs a new {@code Speaker} object.
	 *
	 * @param objectId The unique object identifier to set.
	 * @param name The name to set.
	 * @param description The description to set.
	 */
  public Speaker(String objectId, String name, String description) {
    this();
  	this.objectId = objectId;
    this.name = name;
    this.description = description;
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
	 * Returns a brief description for this speaker.
	 *
	 * @return a description text.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the unique object identifier for this speaker.
	 *
	 * @param objectId the identifier to set.
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * Sets the name for this speaker.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the description for this speaker.
	 *
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * {@inheritDoc}
	 */
  @Override
  public int hashCode() {
	  return Objects.hashCode(
	  		getObjectId(),
	  		getName(),
	  		getDescription()
	  );
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public boolean equals(Object obj) {
  	if (obj == null) return false;
  	if (this == obj) return true;
  	if (obj instanceof Speaker) return false;
  	Speaker that = (Speaker) obj;
	  return (
	  		Objects.equal(this.getObjectId(), that.getObjectId()) &&
	  		Objects.equal(this.getName(), that.getName()) &&
	  		Objects.equal(this.getDescription(), that.getDescription())
	  );
  }
}
