/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.common.base.Objects;
import com.prodyna.pmu.cpa.Conference;

/**
 * {@code Morphia}/{@code MongoDB}-specific implementation of the {@link Conference} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Entity(value="conference", noClassnameStored=true)
public class ConferenceEntity {

	/** The unique object identifier. */
	private @Id ObjectId id;
	
	/** The name of the conference. */
	private String name;
	
	/** A brief description of the conference. */
	private String description;
	
	/** The begin date of the conference. */
	private Date beginDate;
	
	/** The end date of the conference. */
	private Date endDate;
	
	public ObjectId getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the objectId.
	 *
	 * @param id the id to set
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
	  		getId(),
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
  	if (obj instanceof ConferenceEntity) return false;
  	ConferenceEntity that = (ConferenceEntity) obj;
	  return (
	  		Objects.equal(this.getId(), that.getId()) &&
	  		Objects.equal(this.getName(), that.getName()) &&
	  		Objects.equal(this.getDescription(), that.getDescription()) &&
	  		Objects.equal(this.getBeginDate(), that.getBeginDate()) &&
	  		Objects.equal(this.getEndDate(), that.getEndDate())
	  );
  }
}
