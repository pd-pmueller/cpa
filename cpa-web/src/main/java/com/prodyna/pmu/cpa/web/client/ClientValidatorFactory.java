/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client;

import javax.validation.Validator;
import javax.validation.groups.Default;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import com.prodyna.pmu.cpa.web.shared.PortableConference;
import com.prodyna.pmu.cpa.web.shared.PortableRoom;
import com.prodyna.pmu.cpa.web.shared.PortableSpeaker;
import com.prodyna.pmu.cpa.web.shared.PortableTalk;

/**
 * Client {@code ValidatorFactory} for GWT.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class ClientValidatorFactory extends AbstractGwtValidatorFactory {

	/** GwtValidation configuration */
  @GwtValidation(
  		value = { 
  				PortableConference.class,
  				PortableRoom.class,
  				PortableSpeaker.class,
  				PortableTalk.class
  		}, 
  		groups = {
  				Default.class 
  		}
  )
  public interface GwtValidator extends Validator {
  	// Stub
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractGwtValidator createValidator() {
    return GWT.create(GwtValidator.class);
  }
}
