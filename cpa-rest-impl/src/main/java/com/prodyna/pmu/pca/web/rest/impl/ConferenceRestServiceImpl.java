/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.pca.web.rest.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.prodyna.pmu.cpa.domain.Conference;
import com.prodyna.pmu.cpa.ejb.ConferenceService;
import com.prodyna.pmu.pca.web.rest.ConferenceRestService;

/**
 * Implementation of the {@link ConferenceRestService} interface using the {@link ConferenceService} EJB.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@RequestScoped
public class ConferenceRestServiceImpl implements ConferenceRestService {

	/** The service bean to use. */
	private @Inject ConferenceService service;
	
	/**
	 * Returns a list of all available object.
	 * <p>
	 * Note that the implementation is free to limit the number of object returned by this service method.
	 *
	 * @return a list of objects, possibly empty.
	 */
	@Override
	public Iterable<Conference> list() {
		return service.list();
	}
	
	/**
	 * Returns the object with the specified object identifier.
	 *
	 * @param objectId The identifier of the object to return.
	 * @return the corresponding object.
	 */
	@Override
	public Conference read(String objectId) {
		return service.read(objectId);
	}
	
	/**
	 * Inserts a new object.
	 *
	 * @param object The object to store.
	 * @return the stored object.
	 */
	@Override
	public Conference create(Conference object) {
		return service.create(object);
	}
	
	/**
	 * Updates an existing object identified by the specified identifier.
	 *
	 * @param objectId The identifier of the object to update.
	 * @param object The object with which to update.
	 * @return the updated object.
	 */
	@Override
	public Conference update(String objectId, Conference object) {
		return service.update(objectId, object);
	}
	
	/**
	 * Deletes the object with the specified identifier.
	 *
	 * @param objectId The identifier of the object to delete.
	 * @return the deleted object.
	 */
	@Override
	public Conference delete(String objectId) {
		return service.delete(objectId);
	}
}
