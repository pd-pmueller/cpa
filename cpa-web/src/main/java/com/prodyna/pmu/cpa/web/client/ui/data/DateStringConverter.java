/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui.data;

import java.util.Date;

import org.jboss.errai.databinding.client.api.Converter;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

/**
 * Converter for Java {@code Date} objects.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class DateStringConverter implements Converter<Date, String> {

	/** The locale-aware format to use. */
	private DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM);
	
	/** 
	 * {@inheritDoc} 
	 */
  @Override
  public Date toModelValue(String widgetValue) {
    return format.parse(widgetValue);
  }

	/** 
	 * {@inheritDoc} 
	 */
  @Override
  public String toWidgetValue(Date modelValue) {
    return format.format(modelValue);
  }
}
