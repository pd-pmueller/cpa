/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl;

import java.util.Arrays;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.mongodb.morphia.Datastore;

import com.prodyna.pmu.cpa.domain.Speaker;
import com.prodyna.pmu.cpa.ejb.SpeakerServiceBean;
import com.prodyna.pmu.cpa.ejb.domain.SpeakerEntity;

/**
 * EJB service bean implementation of the {@link SpeakerServiceBean} interface.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Local @Stateless
public class SpeakerServiceImpl extends AbstractServiceBeanImpl.Listable<Speaker, SpeakerEntity> 
		implements SpeakerServiceBean {

	private SpeakerEntity create(String name, String description) {
		SpeakerEntity result = new SpeakerEntity();
		result.setName(name);
		result.setDescription(description);
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
  @Override
  public Iterable<Speaker> list() {
  	Datastore ds = getDatastore();
  	if (ds.find(getEntityClass()).countAll() == 0) {
  		// TODO Remove
  		ds.save(Arrays.asList(
  				create("Speaker 1", "An anonymous speaker"),
  				create("Speaker 2", "An anonymous speaker"),
  				create("Speaker 3", "An anonymous speaker"),
  				create("Speaker 4", "An anonymous speaker")
  		));
  	}
	  return super.list();
  }

	/**
	 * {@inheritDoc}
	 */
	@Override
  protected Class<Speaker> getTransferClass() {
	  return Speaker.class;
  }

	/**
	 * {@inheritDoc}
	 */
	@Override
  protected Class<SpeakerEntity> getEntityClass() {
	  return SpeakerEntity.class;
  }
}
