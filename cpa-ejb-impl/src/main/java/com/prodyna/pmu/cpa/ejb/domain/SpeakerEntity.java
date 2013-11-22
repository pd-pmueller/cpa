/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.common.base.Objects;
import com.prodyna.pmu.cpa.domain.Speaker;
import com.prodyna.pmu.cpa.ejb.domain.validation.group.Create;
import com.prodyna.pmu.cpa.ejb.domain.validation.group.Update;

/**
 * {@code Morphia}/{@code MongoDB}-specific implementation of the {@link Speaker} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Entity(value="speaker", noClassnameStored=true)
public class SpeakerEntity {

	/** The unique object identifier. */
	@Null(groups=Create.class) @NotNull(groups=Update.class)
	private @Id ObjectId id;

	/** The name of the speaker. */
	@NotNull
	private String name;
	
	/** A brief description of the speaker. */
	private String description;

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
	 * Returns the name of this speaker.
	 *
	 * @return the full name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this speaker.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Sets a brief description for this speaker.
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
	  		getId(),
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
	  		Objects.equal(this.getId(), that.getObjectId()) &&
	  		Objects.equal(this.getName(), that.getName()) &&
	  		Objects.equal(this.getDescription(), that.getDescription())
	  );
  }
}
