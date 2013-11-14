/*
 * $Id$
 * Copyright 2013 PRODYNA AG 
 */
package com.prodyna.pmu.cpa.web.shared.domain;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Objects;

/**
 * Serializable class that describes a single conference.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class Conference implements HasObjectId, HasName, Serializable {

	/** The {@code serialVersionUID}. */
  private static final long serialVersionUID = -6962966647288883653L;

	/** The unique object identifier. */
	private String objectId;
	
	/** The name of the conference. */
	private String name;
	
	/** A brief description of the conference. */
	private String description;
	
	/** The begin date of the conference. */
	private Date beginDate;
	
	/** The end date of the conference. */
	private Date endDate;
	
	/**
	 * Constructs a new, empty {@code Conference} object.
	 */
	public Conference() {
		super();
	}
	
	/**
	 * Constructs a new {@code Conference} object.
	 *
	 * @param objectId The unique object identifier to set.
	 * @param name The name to set.
	 * @param description The description to set.
	 * @param beginDate The begin date to set.
	 * @param endDate The end date to set.
	 */
  public Conference(String objectId, String name, String description, Date beginDate, Date endDate) {
    this();
  	this.objectId = objectId;
    this.name = name;
    this.description = description;
    this.beginDate = beginDate;
    this.endDate = endDate;
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
	 * Returns a brief description for this conference.
	 *
	 * @return a description text.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the begin date of this conference.
	 *
	 * @return the day the conference begins.
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * Returns the end date of this conference.
	 *
	 * @return the day the conference ends.
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the unique object identifier for this conference.
	 *
	 * @param objectId the identifier to set.
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * Sets the name for this conference.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the description for this conference.
	 *
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the begin date for this conference.
	 *
	 * @param beginDate The date to set.
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * Sets the end date for this conference.
	 *
	 * @param endDate The date to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	  		getBeginDate(),
	  		getEndDate()
	  );
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public boolean equals(Object obj) {
  	if (obj == null) return false;
  	if (this == obj) return true;
  	if (obj instanceof Conference) return false;
  	Conference that = (Conference) obj;
	  return (
	  		Objects.equal(this.getObjectId(), that.getObjectId()) &&
	  		Objects.equal(this.getName(), that.getName()) &&
	  		Objects.equal(this.getDescription(), that.getDescription()) &&
	  		Objects.equal(this.getBeginDate(), that.getBeginDate()) &&
	  		Objects.equal(this.getEndDate(), that.getEndDate())
	  );
  }
}
