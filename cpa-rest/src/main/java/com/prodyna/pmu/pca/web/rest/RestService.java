/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.pca.web.rest;

import com.prodyna.pmu.cpa.domain.HasObjectId;

/**
 * Contract for a CRUD REST service.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <T> The type of object that is serviced.
 */
public interface RestService<T extends HasObjectId> {

	/**
	 * Returns a list of all available object.
	 * <p>
	 * Note that the implementation is free to limit the number of object returned by this service method.
	 *
	 * @return a list of objects, possibly empty.
	 */
	public Iterable<T> list();
	
	/**
	 * Returns the object with the specified object identifier.
	 *
	 * @param objectId The identifier of the object to return.
	 * @return the corresponding object.
	 */
	public T read(String objectId);
	
	/**
	 * Inserts a new object.
	 *
	 * @param object The object to store.
	 * @return the stored object.
	 */
	public T create(T object);
	
	/**
	 * Updates an existing object identified by the specified identifier.
	 *
	 * @param objectId The identifier of the object to update.
	 * @param object The object with which to update.
	 * @return the updated object.
	 */
	public T update(String objectId, T object);
	
	/**
	 * Deletes the object with the specified identifier.
	 *
	 * @param objectId The identifier of the object to delete.
	 * @return the deleted object.
	 */
	public T delete(String objectId);
}
