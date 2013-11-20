/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import javax.ws.rs.PathParam;

import com.prodyna.pmu.cpa.domain.HasObjectId;

/**
 * Base interface for all CRUD service beans within this application.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <E> The entity type that is serviced by the implementing class.
 */
public interface EntityServiceBean<E extends HasObjectId> {

	/**
	 * Extension to the base service, allowing the listing of object.
	 *
	 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
	 * @param <E> The entity type that is serviced by the implementing class.
	 */
	public interface Listable<E extends HasObjectId> extends EntityServiceBean<E> {

		/**
		 * Returns a list of all available object.
		 * <p>
		 * Note that the implementation is free to limit the number of object returned by this service method.
		 *
		 * @return a list of objects, possibly empty.
		 */
		Iterable<E> list();
	}
	
	/**
	 * Returns the object with the specified object identifier.
	 *
	 * @param key The identifier of the object to return.
	 * @return the corresponding object.
	 */
	E read(String key);
	
	/**
	 * Inserts a new object.
	 *
	 * @param object The object to store.
	 * @return the stored object.
	 */
	E create(E object);
	
	/**
	 * Updates an existing object identified by the specified identifier.
	 *
	 * @param key The identifier of the object to update.
	 * @param object The object with which to update.
	 * @return the updated object.
	 */
	E update(@PathParam("{objectId}") String key, E object);
	
	/**
	 * Deletes the object with the specified identifier.
	 *
	 * @param key The identifier of the object to delete.
	 * @return the deleted object.
	 */
	E delete(@PathParam("objectId") String key);
}
