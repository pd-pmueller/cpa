/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client;

import java.lang.reflect.Member;

import javax.validation.Validator;
import javax.validation.groups.Default;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

/**
 * Client {@code ValidatorFactory} for GWT.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class ClientValidatorFactory extends AbstractGwtValidatorFactory {

	/** Validator stub. */
  @GwtValidation(value = Member.class, groups = {Default.class})
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
