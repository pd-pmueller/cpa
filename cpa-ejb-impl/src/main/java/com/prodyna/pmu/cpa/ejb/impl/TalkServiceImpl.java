/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.mongodb.morphia.query.UpdateOperations;

import com.prodyna.pmu.cpa.domain.Talk;
import com.prodyna.pmu.cpa.ejb.TalkService;
import com.prodyna.pmu.cpa.ejb.entity.TalkEntity;

/**
 * EJB service bean implementation of the {@link TalkService} interface.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Local @Stateless
public class TalkServiceImpl extends AbstractServiceImpl.Listable<Talk, TalkEntity> implements TalkService {

	/**
	 * {@inheritDoc}
	 */
	@Override
  protected Class<Talk> getTransferClass() {
	  return Talk.class;
  }

	/**
	 * {@inheritDoc}
	 */
	@Override
  protected Class<TalkEntity> getEntityClass() {
	  return TalkEntity.class;
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  protected void prepare(UpdateOperations<TalkEntity> updateOperations, Talk object) {
		updateOperations
    		.set("name", object.getName())
    		.set("description", object.getDescription())
    		.set("timeLimit", object.getDuration());
  }	
}
