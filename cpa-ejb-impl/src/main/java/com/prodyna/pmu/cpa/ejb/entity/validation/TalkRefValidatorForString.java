/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.entity.validation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.bson.types.ObjectId;

import com.prodyna.pmu.cpa.domain.Conference;
import com.prodyna.pmu.cpa.ejb.ConferenceService;
import com.prodyna.pmu.cpa.ejb.TalkService;

/**
 * Validates the {@link TalkRef} annotation for an object of type {@link ObjectId}.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class TalkRefValidatorForString implements ConstraintValidator<TalkRef, String> {

	/** The {@link TalkService} to use. */
	@Inject
	private TalkService talkService;
	
	/** The {@link ConferenceService} to use. */
	@Inject
	private ConferenceService conferenceService;

	/** Flag whether to check availability. */
	private boolean schedulable = false;
	
	/**
   * {@inheritDoc}
   */
  @Override
  public void initialize(TalkRef constraintAnnotation) {
	  this.schedulable = constraintAnnotation.schedulable();
  }

	/**
   * {@inheritDoc}
   */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
  	if (value == null) return true;
  	boolean result = (talkService.read(value) != null);
	  if (result && schedulable) {
	  	for (Conference c : conferenceService.list()) {
	  		if (c.getTalks().contains(value)) {
	  			result = false;
	  			break;
	  		}
	  	}
	  }
	  return result;
  }
}
