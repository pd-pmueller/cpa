/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.cdi;

import java.util.concurrent.TimeUnit;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

import org.bson.types.ObjectId;

import com.prodyna.pmu.cpa.domain.Conference;
import com.prodyna.pmu.cpa.domain.Room;
import com.prodyna.pmu.cpa.domain.Speaker;
import com.prodyna.pmu.cpa.domain.Talk;
import com.prodyna.pmu.cpa.ejb.domain.ConferenceEntity;
import com.prodyna.pmu.cpa.ejb.domain.RoomEntity;
import com.prodyna.pmu.cpa.ejb.domain.SpeakerEntity;
import com.prodyna.pmu.cpa.ejb.domain.TalkEntity;

/**
 * Producer for {@code Orika}-related objects.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class OrikaProducer {

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
					.fieldMap("duration").converter("duration").add()
					.byDefault()
					.toClassMap()
		);
		// Converters
		ConverterFactory converters = factory.getConverterFactory();
		// Fields
		converters.registerConverter("duration", new BidirectionalConverter<Long, Integer>() {
			@Override public Integer convertTo(Long source, Type<Integer> destinationClass) {
	      return Integer.valueOf(Long.toString(TimeUnit.MINUTES.convert(source, TimeUnit.MILLISECONDS)));
      }
			@Override public Long convertFrom(Integer source, Type<Long> destinationClass) {
	      return Long.valueOf(TimeUnit.MILLISECONDS.convert(source, TimeUnit.MINUTES));
      }
		});
		// ObjectId (global)
		converters.registerConverter(new CustomConverter<String, ObjectId>() {
			@Override public ObjectId convert(String source, Type<? extends ObjectId> destinationClass) {
	      return new ObjectId(source);
      }
		});
		converters.registerConverter(new CustomConverter<ObjectId, String>() {
			@Override public String convert(ObjectId source, Type<? extends String> destinationClass) {
	      return source.toString();
      }
		});
		// Done
		return factory.getMapperFacade();
	}
	
}
