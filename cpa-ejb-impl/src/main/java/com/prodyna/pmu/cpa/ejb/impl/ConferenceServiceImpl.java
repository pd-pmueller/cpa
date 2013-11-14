/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.UpdateOperations;

import com.prodyna.pmu.cpa.domain.Conference;
import com.prodyna.pmu.cpa.ejb.ConferenceService;
import com.prodyna.pmu.cpa.ejb.entity.ConferenceEntity;

/**
 * EJB service bean implementation of the {@link ConferenceService} interface.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Local @Stateless
public class ConferenceServiceImpl extends AbstractServiceImpl.Listable<Conference, ConferenceEntity> 
		implements ConferenceService {

	private ConferenceEntity create(String name, String description, Date beginDate, Date endDate) {
		ConferenceEntity result = new ConferenceEntity();
		result.setName(name);
		result.setDescription(description);
		result.setBeginDate(beginDate);
		result.setEndDate(endDate);
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
  @Override
  public Iterable<Conference> list() {
  	Datastore ds = getDatastore();
  	if (ds.find(getEntityClass()).countAll() == 0) {
  		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  		try {
  		ds.save(Arrays.asList(
  				create("Test 1", "Description 1", f.parse("2013-01-01 00:00:00"), f.parse("2013-01-03 00:00:00")),
  				create("Test 2", "Description 2", f.parse("2013-02-01 00:00:00"), f.parse("2013-02-03 00:00:00")),
  				create("Test 3", "Description 3", f.parse("2013-03-01 00:00:00"), f.parse("2013-03-03 00:00:00")),
  				create("Test 4", "Description 4", f.parse("2013-04-01 00:00:00"), f.parse("2013-04-03 00:00:00"))
  		));
  		}
  		catch (ParseException e) {
  			// Ignore
  		}
  	}
  	
	  return super.list();
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  protected Class<Conference> getTransferClass() {
	  return Conference.class;
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  protected Class<ConferenceEntity> getEntityClass() {
	  return ConferenceEntity.class;
  }
  
	/**
	 * {@inheritDoc}
	 */
  @Override
  protected void prepare(UpdateOperations<ConferenceEntity> updateOperations, Conference object) {
		updateOperations
    		.set("name", object.getName())
    		.set("description", object.getDescription())
    		.set("beginDate", object.getBeginDate())
    		.set("endDate", object.getEndDate());
  }
}