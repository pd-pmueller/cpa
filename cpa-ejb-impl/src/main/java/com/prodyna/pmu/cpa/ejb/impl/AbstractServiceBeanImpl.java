/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.impl;

import javax.inject.Inject;

import ma.glasnost.orika.MapperFacade;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.prodyna.pmu.cpa.domain.HasObjectId;
import com.prodyna.pmu.cpa.ejb.EntityServiceBean;

/**
 * Abstract implementation of the {@link EntityServiceBean} interface.
 * <p>
 * This abstract class should reduce the concrete service implementation significantly.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <T> The transferable object class which is serviced.
 * @param <E> The entity object class to use.
 */
public abstract class AbstractServiceBeanImpl<T extends HasObjectId, E> implements EntityServiceBean<T> {

	/**
	 * Abstract implementation of the {@link com.prodyna.pmu.cpa.ejb.EntityServiceBean.Listable} interface.
	 *
   * @param <T> The entity interface which is serviced.
   * @param <E> The entity implementation to use.
	 */
	public static abstract class Listable<T extends HasObjectId, E> extends AbstractServiceBeanImpl<T, E>
			implements EntityServiceBean.Listable<T> {
		
		/** The default order. */
		private static final String DEFAULT_ORDER = "id";

		/** The default list limit. */
		private static final int DEFAULT_LIMIT = 100;
		
	  /**
		 * {@inheritDoc}
		 */
	  @Override
	  public Iterable<T> list() {
	  	Iterable<E> result = getDatastore().find(getEntityClass())
	  			.order(getDefaultOrder())
	  			.limit(getDefaultLimit())
	  			.fetch();
		  return transform(result);
	  }

	  /**
		 * {@inheritDoc}
		 */
	  @Override
	  public Iterable<T> resolve(Iterable<String> list) {
	  	Iterable<E> result = getDatastore().find(getEntityClass())
	  			.filter(
	  					"id", 
	  					Iterables.transform(list, new Function<String, ObjectId>() {
    	  		  	@Override public ObjectId apply(String input) {
    	  		  	  return new ObjectId(input);
    	  		  	}
    	  		  })
	  		  )
	  			.order(getDefaultOrder())
	  			.limit(getDefaultLimit())
	  			.fetch();
	  	return transform(result);
	  }

	  /**
	   * Returns the default order to apply if none is specified.
	   * <p>
	   * This method can be safely overridden by subclasses.
	   *
	   * @return the default order.
	   */
	  protected String getDefaultOrder() {
	  	return DEFAULT_ORDER;
	  }
	  
	  /**
	   * Returns the default limit for queries if none is specified.
	   * <p>
	   * This method can be safely overridden by subclasses.
	   *
	   * @return the default fetch limit.
	   */
	  protected int getDefaultLimit() {
	  	return DEFAULT_LIMIT;
	  }
	  
	  /**
	   * Transforms the specified iterable of entity object to their transferable counterparts.
	   *
	   * @param iterable The iterable of entity objects.
	   * @return the corresponding iterable of transferable objects.
	   */
	  protected Iterable<T> transform(Iterable<E> iterable) {
		  return Iterables.transform(iterable, new Function<E, T>() {
		  	@Override public T apply(E input) {
		  	  return map(input);
		  	}
		  });
	  }
	}

	/** The underlying datastore object. */
	@Inject
	private Datastore datastore;
	
	/** The object mapper facade to use. */
	@Inject
	private MapperFacade mapper;
	
	/**
	 * {@inheritDoc}
	 */
  @Override
  public T read(String objectId) {
  	return map(queryById(objectId).get());
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public T create(T object) {
		E entity = map(object);
		// Save
  	datastore.save(entity);
  	// Return
		return map(entity);
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public T update(String objectId, T object) {
  	if (object.getObjectId() != null && !object.getObjectId().equals(objectId))
  		throw new RuntimeException("ObjectKey does not match requested instance"); // TODO Proper exception
  	// Update
  	E entity = map(object);
		datastore.updateFirst(queryById(objectId), entity, false);
		return map(entity);
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public T delete(String objectId) {
	  return map(datastore.findAndDelete(queryById(objectId)));
  }
  
	/**
	 * Returns the underlying datastore object.
	 *
	 * @return a datastore object.
	 */
	protected Datastore getDatastore() {
		return datastore;
	}
	
	/**
	 * Returns the class of the serviced transferable object.
	 *
	 * @return an interface class.
	 */
	protected abstract Class<T> getTransferClass();
	
	/**
	 * Returns the implementation class of the serviced entity.
	 *
	 * @return an implementation class.
	 */
	protected abstract Class<E> getEntityClass();

	/**
	 * Returns the default query to execute when looking up an identifier.
	 *
	 * @param objectId The object identifier to query for.
	 * @return the default query.
	 */
	protected Query<E> queryById(String objectId) {
  	return datastore.createQuery(getEntityClass()).field("id").equal(new ObjectId(objectId));
  }
	
	/**
	 * Maps from a transferable object instance to its entity counterpart.
	 *
	 * @param object The object to map.
	 * @return the corresponding entity object.
	 */
	protected E map(T object) {
		return mapper.map(object, getEntityClass());
	}
	
	/**
	 * Maps from an entity object instance to its transferable counterpart.
	 *
	 * @param object The object to map.
	 * @return the corresponding transferable object.
	 */
	protected T map(E object) {
		return mapper.map(object, getTransferClass());
	}
}
