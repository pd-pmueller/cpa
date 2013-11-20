/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.pca.web.rest.impl;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.prodyna.pmu.cpa.domain.Room;
import com.prodyna.pmu.cpa.ejb.RoomService;
import com.prodyna.pmu.cpa.web.rest.RoomRestService;

/**
 * Implementation of the {@link RoomRestService} interface using the {@link RoomService} EJB.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@RequestScoped
public class RoomRestServiceImpl implements RoomRestService {

	/** The service bean to use. */
	private @Inject RoomService service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Room> list() {
		return Lists.newArrayList(service.list());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Room read(String objectId) {
		return service.read(objectId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Room create(Room object) {
		return service.create(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Room update(String objectId, Room object) {
		return service.update(objectId, object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Room delete(String objectId) {
		return service.delete(objectId);
	}
}
