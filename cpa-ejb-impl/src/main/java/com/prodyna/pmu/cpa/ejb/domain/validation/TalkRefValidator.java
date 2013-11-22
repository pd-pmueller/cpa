/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.bson.types.ObjectId;

import com.prodyna.pmu.cpa.domain.Conference;
import com.prodyna.pmu.cpa.ejb.ConferenceServiceBean;
import com.prodyna.pmu.cpa.ejb.SpeakerServiceBean;
import com.prodyna.pmu.cpa.ejb.TalkServiceBean;
import com.prodyna.pmu.cpa.ejb.cdi.DestructibleBean;

/**
 * Abstract validator for {@code @TalkRef} constraints.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <T> The value type.
 */
public abstract class TalkRefValidator<T> implements ConstraintValidator<TalkRef, T> {

	/**
	 * Implementation of the {@link TalkRefValidator} class for values of type {@link String}.
	 */
	public static final class ForString extends TalkRefValidator<String> {

		/**
		 * {@inheritDoc}
		 */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	    return super.validate(value, context);
    }
	}

	/**
	 * Implementation of the {@link SpeakerRefValidator} class for values of type {@code Iterable<ObjectId>}.
	 */
	public static class ForIterableOfString extends TalkRefValidator<Iterable<String>> {

		/**
		 * {@inheritDoc}
		 */
    @Override
    public boolean isValid(Iterable<String> value, ConstraintValidatorContext context) {
    	for (String objectId : value) {
    		if (!super.validate(objectId, context)) return false;
    	}
    	return true;
    }
	}

	/**
	 * Implementation of the {@link TalkRefValidator} class for values of type {@link ObjectId}.
	 */
	public static final class ForObjectId extends TalkRefValidator<ObjectId> {

		/**
		 * {@inheritDoc}
		 */
    @Override
    public boolean isValid(ObjectId value, ConstraintValidatorContext context) {
	    return super.validate(value.toString(), context);
    }
	}

	/**
	 * Implementation of the {@link SpeakerRefValidator} class for values of type {@code Iterable<ObjectId>}.
	 */
	public static class ForIterableOfObjectId extends TalkRefValidator<Iterable<ObjectId>> {

		/**
		 * {@inheritDoc}
		 */
    @Override
    public boolean isValid(Iterable<ObjectId> value, ConstraintValidatorContext context) {
    	for (ObjectId objectId : value) {
    		if (!super.validate(objectId.toString(), context)) return false;
    	}
    	return true;
    }
	}

	/** The {@link SpeakerServiceBean} to use. */
	private DestructibleBean<TalkServiceBean> talkServiceBean;

	/** The {@link SpeakerServiceBean} to use. */
	private DestructibleBean<ConferenceServiceBean> conferenceServiceBean;
	
	/** Flag whether to check availability. */
	private boolean available = false;
	
	/**
	 * Initializes this validator instance with the specified annotation.
	 *
	 * @param constraintAnnotation The annotation to process.
	 */
	@Override
  public void initialize(TalkRef constraintAnnotation) {
	  this.available = constraintAnnotation.available();
  }

  /**
   * Performs the validation.
   *
   * @param value The value to validate.
   * @param context The context in which the validation is processed.
   * @return {@code true} if the value is valid; {@code false} otherwise.
   */
  protected boolean validate(String value, ConstraintValidatorContext context) {
	  if (value == null) return true;
	  // Validate reference
  	boolean result = (getTalkService().read(value.toString()) != null);
	  if (result && available) {
	  	// Validate schedulable
	  	// TODO Sledgehammer approach
	  	for (Conference c : getConferenceService().list()) {
	  		if (c.getTalks().contains(value.toString())) {
	  			result = false;
	  			break;
	  		}
	  	}
	  }
	  return result;
  }

  /**
   * Returns the {@link TalkServiceBean} instance to use.
   *
   * @return a {@link TalkServiceBean} instance.
   */
  private TalkServiceBean getTalkService() {
  	if (talkServiceBean == null) {
  		talkServiceBean = DestructibleBean.resolve(TalkServiceBean.class);
  	}
  	return talkServiceBean.getInstance();
  }

  /**
   * Returns the {@link ConferenceServiceBean} instance to use.
   *
   * @return a {@link ConferenceServiceBean} instance.
   */
  private ConferenceServiceBean getConferenceService() {
  	if (talkServiceBean == null) {
  		conferenceServiceBean = DestructibleBean.resolve(ConferenceServiceBean.class);
  	}
  	return conferenceServiceBean.getInstance();
  }

  /**
   * {@inheritDoc}
   */
	@Override
  protected void finalize() throws Throwable {
		// Destruct beans
		talkServiceBean.destroy();
		conferenceServiceBean.destroy();
		// Done
	  super.finalize();
  }
}
