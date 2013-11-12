/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.producer;

import java.net.UnknownHostException;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * This class produces all dependencies for the project.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class DependencyProducer {

	/** The static morphia instance. */
	private static Morphia morphia = null;
	
	/**
	 * Produces and returns a morphia {@code Datastore} instance.
	 *
	 * @return a {@code Datastore} instance.
	 */
	@Produces
	public Datastore getDatastore() {
		return getMorphia().createDatastore(getMongo(), "conference");
	}
	
	/**
	 * Produces and returns a {@code Morphia} instance.
	 *
	 * @return a {@code Morphia} instance.
	 */
	@Produces
	public Morphia getMorphia() {
		if (morphia == null) {
  		morphia = new Morphia();
  		morphia.mapPackage("com.prodyna.pmu.cpa.ejb.entity");
		}
		return morphia;
	}

	/**
	 * Produces and returns a {@code Mongo} client instance.
	 *
	 * @return a {@code Mongo} instance.
	 */
	@Produces
	public Mongo getMongo() {
		try {
			return new MongoClient("localhost");
		}
		catch (UnknownHostException e) {
			throw Throwables.propagate(e);
		}
	}
	
	/**
	 * Produces and returns an SLF4J {@code Logger} instance.
	 *
	 * @param injectionPoint The injection point.
	 * @return a {@code Logger} instance.
	 */
	@Produces 
  public Logger getLogger(final InjectionPoint injectionPoint) { 
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass()); 
  }
}
