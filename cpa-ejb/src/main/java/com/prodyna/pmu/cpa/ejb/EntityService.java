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

import com.prodyna.pmu.cpa.common.entity.BaseEntity;

/**
 * Base interface for all RESTful CRUD services within this application.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <T> The entity type that is serviced by the implementing class.
 */
public interface EntityService<T extends BaseEntity> {

	/**
	 * Extension to the base service, allowing the listing of object.
	 *
	 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
	 * @param <T> The entity type that is serviced by the implementing class.
	 */
	public interface Listable<T extends BaseEntity> extends EntityService<T> {

		/**
		 * Returns a list of all available object.
		 * <p>
		 * Note that the implementation is free to limit the number of object returned by this service method.
		 *
		 * @return a list of objects, possibly empty.
		 */
		@GET @Produces(MediaType.APPLICATION_JSON)
		Iterable<T> list();
	}
	
	/**
	 * Returns the object with the specified object identifier.
	 *
	 * @param objectId The identifier of the object to return.
	 * @return the corresponding object.
	 */
	@GET @Path("{objectId}") @Produces(MediaType.APPLICATION_JSON)
	T read(@PathParam("objectId") String objectId);
	
	/**
	 * Inserts a new object.
	 *
	 * @param object The object to store.
	 * @return the stored object.
	 */
	@POST @Consumes(MediaType.APPLICATION_JSON)
	T create(T object);
	
	/**
	 * Updates an existing object identified by the specified identifier.
	 *
	 * @param objectId The identifier of the object to update.
	 * @param object The object with which to update.
	 * @return the updated object.
	 */
	@PUT @Path("{objectId}") @Produces(MediaType.APPLICATION_JSON)
	T update(@PathParam("{objectId}") String objectId, T object);
	
	/**
	 * Deletes the object with the specified identifier.
	 *
	 * @param objectId The identifier of the object to delete.
	 * @return the deleted object.
	 */
	@DELETE @Path("{objectId}") @Produces(MediaType.APPLICATION_JSON)
	T delete(@PathParam("objectId") String objectId);
}
