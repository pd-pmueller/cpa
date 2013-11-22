/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.domain;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import com.google.common.base.Objects;
import com.prodyna.pmu.cpa.domain.Talk;
import com.prodyna.pmu.cpa.ejb.domain.validation.SpeakerRef;
import com.prodyna.pmu.cpa.ejb.domain.validation.group.Create;
import com.prodyna.pmu.cpa.ejb.domain.validation.group.Update;

/**
 * {@code Morphia}/{@code MongoDB}-specific implementation of the {@link Talk} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Entity(value="talk", noClassnameStored=true)
public class TalkEntity {

	/** The unique object identifier. */
	@Null(groups=Create.class) @NotNull(groups=Update.class)
	private @Id ObjectId id;

	/** The name of the talk. */
	@NotNull
	private String name;

	/** A brief description of the talk. */
	private String description;
	
	/** The scheduled time. */
	@NotNull @Future(groups=Create.class)
	private Date time;
	
	/** The duration in milliseconds. */
	@NotNull @Min(1800000) @Max(43200000)
	private Long duration;
	
	/** The room that this talk is held in. */
	private ObjectId room;

	/** The speakers assigned to this talk. */
	@SpeakerRef
	@Property(concreteClass = java.util.TreeSet.class)
	private Set<ObjectId> speakers = new LinkedHashSet<ObjectId>();

	/**
	 * Returns the implementation-specific unique object identifier.
	 *
	 * @return the object identifier.
	 */
	public ObjectId getId() {
		return id;
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
	 * Returns the name of this talk.
	 *
	 * @return the short name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this talk.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Sets a brief description for this talk.
	 *
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the scheduled time of this talk, if any.
	 *
	 * @return the scheduled time.
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Sets the scheduled time of this talk.
	 *
	 * @param time The time to set.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * Returns the duration of this talk.
	 *
	 * @return the duration, in milliseconds.
	 */
	public Long getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of this talk.
	 *
	 * @param duration The duration to set, in milliseconds. 
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	/**
	 * Returns the room this talk is assigned to, if any.
	 *
	 * @return a room identifier or {@code null} if none is set.
	 */
	public ObjectId getRoom() {
		return room;
	}

	/**
	 * Sets the room this talk is assigned to.
	 *
	 * @param room The room identifier to set.
	 */
	public void setRoom(ObjectId room) {
		this.room = room;
	}

	/**
	 * Returns the speakers assigned to this talk.
	 *
	 * @return a set of speaker identifiers.
	 */
	public Set<ObjectId> getSpeakers() {
		return speakers;
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
	  		getRoom(),
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
	  		Objects.equal(this.getRoom(), that.getRoom()) &&
	  		Objects.equal(this.getSpeakers(), that.getSpeakers())
	  );
  }
}
