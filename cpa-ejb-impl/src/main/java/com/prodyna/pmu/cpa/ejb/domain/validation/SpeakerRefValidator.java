/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.bson.types.ObjectId;

import com.prodyna.pmu.cpa.ejb.SpeakerServiceBean;
import com.prodyna.pmu.cpa.ejb.cdi.DestructibleBean;

/**
 * Abstract validator for {@code @TalkRef} constraints.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <T> The value type.
 */
public abstract class SpeakerRefValidator<T> implements ConstraintValidator<SpeakerRef, T> {

	/**
	 * Implementation of the {@link SpeakerRefValidator} class for values of type {@link String}.
	 */
	public static class ForString extends SpeakerRefValidator<String> {

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
	public static class ForIterableOfString extends SpeakerRefValidator<Iterable<String>> {

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
	 * Implementation of the {@link SpeakerRefValidator} class for values of type {@link ObjectId}.
	 */
	public static class ForObjectId extends SpeakerRefValidator<ObjectId> {

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
	public static class ForIterableOfObjectId extends SpeakerRefValidator<Iterable<ObjectId>> {

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
	private DestructibleBean<SpeakerServiceBean> speakerService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
  public void initialize(SpeakerRef constraintAnnotation) {
  	// Nothing to do
  }

  /**
   * Performs the validation.
   *
   * @param value The value to validate.
   * @param context The context in which the validation is processed.
   * @return {@code true} if the value is valid; {@code false} otherwise.
   */
  public boolean validate(String value, ConstraintValidatorContext context) {
	  if (value == null) return true;
	  // Validate reference
  	return (getSpeakerService().read(value) != null);
  }
  
  /**
   * Returns the {@link SpeakerServiceBean} instance to use.
   *
   * @return a {@link SpeakerServiceBean} instance.
   */
  private SpeakerServiceBean getSpeakerService() {
  	if (speakerService == null) {
  		speakerService = DestructibleBean.resolve(SpeakerServiceBean.class);
  	}
  	return speakerService.getInstance();
  }

  /**
   * {@inheritDoc}
   */
	@Override
  protected void finalize() throws Throwable {
		// Destruct beans
		speakerService.destroy();
		// Done
	  super.finalize();
  }
}
