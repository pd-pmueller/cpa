/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prodyna.pmu.cpa.domain.HasObjectId;
import com.prodyna.pmu.cpa.ejb.intercept.Logged;
import com.prodyna.pmu.cpa.ejb.intercept.Monitored;

/**
 * Base interface for all CRUD service beans within this application.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <E> The entity type that is serviced by the implementing class.
 */
@Logged @Monitored
public interface EntityServiceBean<E extends HasObjectId> {

	/**
	 * Extension to the base service, allowing the listing of object.
	 *
	 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
	 * @param <E> The entity type that is serviced by the implementing class.
	 */
	@Logged @Monitored
	public interface Listable<E extends HasObjectId> extends EntityServiceBean<E> {

		/**
		 * Returns a list of all available object.
		 * <p>
		 * Note that the implementation is free to limit the number of object returned by this service method.
		 *
		 * @return a list of objects, possibly empty.
		 */
		@GET @Produces(MediaType.APPLICATION_JSON)
		Iterable<E> list();

	  /**
	   * Resolves a list of object identifiers to their corresponding objects.
	   *
	   * @param list The list of object identifiers to resolve. 
	   * @return the corresponding objects.
	   */
		Iterable<E> resolve(Iterable<String> list);
	}
	
	/**
	 * Returns the object with the specified object identifier.
	 *
	 * @param key The identifier of the object to return.
	 * @return the corresponding object.
	 */
	@GET @Path("{objectId}") @Produces(MediaType.APPLICATION_JSON)
	E read(@PathParam("objectId") String key);
	
	/**
	 * Inserts a new object.
	 *
	 * @param object The object to store.
	 * @return the stored object.
	 */
	@POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	E create(E object);
	
	/**
	 * Updates an existing object identified by the specified identifier.
	 *
	 * @param key The identifier of the object to update.
	 * @param object The object with which to update.
	 * @return the updated object.
	 */
	@PUT @Path("{objectId}") @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	E update(@PathParam("objectId") String key, E object);
	
	/**
	 * Deletes the object with the specified identifier.
	 *
	 * @param key The identifier of the object to delete.
	 * @return the deleted object.
	 */
	@DELETE @Path("{objectId}") @Produces(MediaType.APPLICATION_JSON)
	E delete(@PathParam("objectId") String key);
}
