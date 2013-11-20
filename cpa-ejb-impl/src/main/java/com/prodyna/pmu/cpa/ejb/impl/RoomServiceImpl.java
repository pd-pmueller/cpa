/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl;

import java.util.Arrays;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.UpdateOperations;

import com.prodyna.pmu.cpa.domain.Room;
import com.prodyna.pmu.cpa.ejb.RoomService;
import com.prodyna.pmu.cpa.ejb.entity.RoomEntity;

/**
 * EJB service bean implementation of the {@link RoomService} interface.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Local @Stateless
public class RoomServiceImpl extends AbstractServiceImpl.Listable<Room, RoomEntity> implements RoomService {

	private RoomEntity create(String name, Integer capacity) {
		RoomEntity result = new RoomEntity();
		result.setName(name);
		result.setCapacity(capacity);
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
  @Override
  public Iterable<Room> list() {
  	Datastore ds = getDatastore();
  	if (ds.find(getEntityClass()).countAll() == 0) {
  		// TODO Remove
  		ds.save(Arrays.asList(
  				create("Room 1", 10),
  				create("Room 2", 15),
  				create("Room 3", 5),
  				create("Room 4", 30)
  		));
  	}
	  return super.list();
  }

	/**
	/**
	 * {@inheritDoc}
	 */
  @Override
  protected Class<Room> getTransferClass() {
	  return Room.class;
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  protected Class<RoomEntity> getEntityClass() {
	  return RoomEntity.class;
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  protected void prepare(UpdateOperations<RoomEntity> updateOperations, Room object) {
		updateOperations
    		.set("name", object.getName())
    		.set("capacity", object.getCapacity());
  }
}
