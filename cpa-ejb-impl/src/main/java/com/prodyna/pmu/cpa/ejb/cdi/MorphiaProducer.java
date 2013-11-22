/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.cdi;

import java.net.UnknownHostException;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

import com.google.common.base.Throwables;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Producer for {@code Morphia}-related objects.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class MorphiaProducer {

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
	@Produces @Singleton
	public Morphia getMorphia() {
		Morphia morphia = new Morphia();
		// Mappings
		morphia.mapPackage("com.prodyna.pmu.cpa.domain.ejb.entity");
		// Extensions
		new ValidationExtension(morphia);
		// Done
		return morphia;
	}

	/**
	 * Produces and returns a {@code Mongo} client instance.
	 *
	 * @return a {@code Mongo} instance.
	 */
	@Produces @Singleton
	public Mongo getMongo() {
		try {
			return new MongoClient("localhost");
		}
		catch (UnknownHostException e) {
			throw Throwables.propagate(e);
		}
	}
}
