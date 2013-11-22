/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.domain.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validation for a talk reference.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy={
		TalkRefValidator.ForObjectId.class,
		TalkRefValidator.ForIterableOfObjectId.class,
		TalkRefValidator.ForString.class,
		TalkRefValidator.ForIterableOfString.class
})
public @interface TalkRef {
	
	/**
	 * Checks if the talk is still available to be scheduled.
	 *
	 * @return {@code true} to perform the availability check; {@code false} otherwise.
	 */
	boolean available() default false;

	/**
	 * The message to use.
	 * 
	 * @return the message.
	 */
	String message() default "{com.prodyna.pmu.cpa.ejb.domain.validation.TalkRef.message}";

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

	/**
	 * Defines several {@code @TalkRef} annotations on the same element.
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		
		/**
		 * The list of {@link TalkRef}s.
		 *
		 * @return the list.
		 */
		TalkRef[] value();
	}
}
