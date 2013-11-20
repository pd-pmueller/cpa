/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import java.net.UnknownHostException;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.prodyna.pmu.cpa.domain.Conference;
import com.prodyna.pmu.cpa.domain.Room;
import com.prodyna.pmu.cpa.domain.Speaker;
import com.prodyna.pmu.cpa.domain.Talk;
import com.prodyna.pmu.cpa.ejb.entity.ConferenceEntity;
import com.prodyna.pmu.cpa.ejb.entity.RoomEntity;
import com.prodyna.pmu.cpa.ejb.entity.SpeakerEntity;
import com.prodyna.pmu.cpa.ejb.entity.TalkEntity;

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
	@Produces @Singleton
	public Morphia getMorphia() {
		if (morphia == null) {
  		morphia = new Morphia();
  		morphia.mapPackage("com.prodyna.pmu.cpa.domain.ejb.entity");
		}
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
	
	/**
	 * Produces and returns a {@code Orika} {@code MapperFacade} instance.
	 *
	 * @return a {@code MapperFacade} instance.
	 */
	@Produces @Singleton
	public MapperFacade getMapperFacade() {
		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		// Mappable classes
		factory.registerClassMap(
				factory.classMap(Conference.class, ConferenceEntity.class)
					.field("objectId", "id")
					.byDefault()
					.toClassMap()
		);
		factory.registerClassMap(
				factory.classMap(Room.class, RoomEntity.class)
					.field("objectId", "id")
					.byDefault()
					.toClassMap()
		);
		factory.registerClassMap(
				factory.classMap(Speaker.class, SpeakerEntity.class)
					.field("objectId", "id")
					.byDefault()
					.toClassMap()
		);
		factory.registerClassMap(
				factory.classMap(Talk.class, TalkEntity.class)
					.field("objectId", "id")
					.byDefault()
					.toClassMap()
		);
		// Converters
		ConverterFactory converters = factory.getConverterFactory();
		converters.registerConverter(new CustomConverter<String, ObjectId>() {
			@Override public ObjectId convert(String source, Type<? extends ObjectId> destinationClass) {
	      return new ObjectId(source);
      }
		});
		// Done
		return factory.getMapperFacade();
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
