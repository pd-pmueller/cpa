/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.mongodb.morphia.query.UpdateOperations;

import com.prodyna.pmu.cpa.Speaker;
import com.prodyna.pmu.cpa.ejb.SpeakerService;
import com.prodyna.pmu.cpa.ejb.entity.SpeakerEntity;

/**
 * EJB service bean implementation of the {@link SpeakerService} interface.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Local @Stateless
public class SpeakerServiceImpl extends AbstractServiceImpl.Listable<Speaker, SpeakerEntity> 
		implements SpeakerService {

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

	/**
	 * {@inheritDoc}
	 */
  @Override
  protected void prepare(UpdateOperations<SpeakerEntity> updateOperations, Speaker object) {
		updateOperations
    		.set("name", object.getName())
    		.set("description", object.getDescription());
  }	
}
