/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.entity.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validation for a talk.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy={
		TalkRefValidatorForObjectId.class,
		TalkRefValidatorForString.class
})
public @interface TalkRef {
	
	/**
	 * Checks if the talk is still available to be scheduled.
	 *
	 * @return {@code true} to perform the check; {@code false} otherwise.
	 */
	boolean schedulable() default false;

	/**
	 * The message to use.
	 * 
	 * @return the message.
	 */
	String message() default "{com.prodyna.pmu.cpa.ejb.entity.validation.Talk.message}";

	/**
	 * The validation groups this applies to.
	 * 
	 * @return the groups.
	 */
  Class<?>[] groups() default {};

  /**
   * The validation payload.
   * 
   * @return the payload class.
   */
  Class<? extends Payload>[] payload() default {};
}
