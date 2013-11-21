/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.entity;

import java.util.Date;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import com.google.common.base.Objects;
import com.prodyna.pmu.cpa.domain.Conference;

/**
 * {@code Morphia}/{@code MongoDB}-specific implementation of the {@link Conference} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Entity(value="conference", noClassnameStored=true)
public class ConferenceEntity {

	/** The implementation-specific unique object identifier. */
	private @Id ObjectId id;
	
	/** The name of the conference. */
	private String name;
	
	/** A brief description of the conference. */
	private String description;
	
	/** The begin date of the conference. */
	private Date beginDate;
	
	/** The end date of the conference. */
	private Date endDate;
	
	/** The talks scheduled for this conference. */
	@Property(concreteClass = java.util.TreeSet.class)
	private Set<ObjectId> talks;
	
	/**
	 * Returns the implementation-specific object identifier.
	 *
	 * @return the object identifier.
	 */
	public ObjectId getId() {
		return id;
	}
	
	/**
	 * Returns the name of this conference.
	 *
	 * @return a name.
	 */
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
	 * @return a date.
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * Returns the end date of this conference.
	 *
	 * @return a date.
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Returns the talks scheduled for this conference.
	 *
	 * @return the talks of this conference.
	 */
	public Set<ObjectId> getTalks() {
		return talks;
	}
	
	/**
	 * Sets the implementation-specific object identifier.
	 *
	 * @param id The identifier to set.
	 */
	public void setId(ObjectId id) {
		this.id = id;
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
	  		getId(),
	  		getName(),
	  		getDescription(),
	  		getBeginDate(),
	  		getEndDate(),
	  		getTalks()
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
	  		Objects.equal(this.getEndDate(), that.getEndDate()) &&
	  		Objects.equal(this.getTalks(), that.getTalks())
	  );
  }
}
