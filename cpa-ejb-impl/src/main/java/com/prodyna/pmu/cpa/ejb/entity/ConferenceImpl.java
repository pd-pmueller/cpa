/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.entity;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.common.base.Objects;
import com.prodyna.pmu.cpa.common.entity.Conference;

/**
 * Morphia-specific implementation of the {@link Conference} entity interface.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Entity(value="conference", noClassnameStored=true)
public class ConferenceImpl implements Conference {

	/** The unique object identifier. */
	private @Id String id;
	
	/** The name of the conference. */
	private String name;
	
	/** A brief description of the conference. */
	private String description;
	
	/** The begin date of the conference. */
	private Date beginDate;
	
	/** The end date of the conference. */
	private Date endDate;
	
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
	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getEndDate() {
		return endDate;
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
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the beginDate.
	 *
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * Sets the endDate.
	 *
	 * @param endDate the endDate to set
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
