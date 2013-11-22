/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl;

import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.bson.types.ObjectId;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.prodyna.pmu.cpa.domain.Talk;
import com.prodyna.pmu.cpa.ejb.TalkServiceBean;
import com.prodyna.pmu.cpa.ejb.domain.TalkEntity;

/**
 * EJB service bean implementation of the {@link TalkServiceBean} interface.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Local @Stateless
public class TalkServiceImpl extends AbstractServiceBeanImpl.Listable<Talk, TalkEntity> implements TalkServiceBean {

	/**
	 * {@inheritDoc}
	 */
  @Override
  public Iterable<Talk> forSpeaker(String objectId) {
  	Iterable<TalkEntity> result = getDatastore().find(getEntityClass())
  			.filter("speakers", new ObjectId(objectId))
  			.order(getDefaultOrder())
  			.limit(getDefaultLimit())
  			.fetch();
  	return transform(result);
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public Iterable<Talk> forTimeslot(final Date min, final Date max) {
  	Iterable<TalkEntity> result = getDatastore().find(getEntityClass())
  			.filter("time <=", max)
  			.order(getDefaultOrder())
  			.limit(getDefaultLimit())
  			.fetch();
  	return transform(Iterables.filter(result, new Predicate<TalkEntity>() {
			@Override public boolean apply(TalkEntity input) {
	      return (input.getTime().getTime() + input.getDuration() < min.getTime());
      }
		}));
  }

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
}
