/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.shared.event;

import org.jboss.errai.bus.client.api.Local;
import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Event object that is raised whenever a page is shown.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Portable @Local
public class PageChange {

	/** The class of the page that is shown. */
	private String pageClass;

	/**
	 * Constructs a new {@code PageShown} object.
	 */
	public PageChange() {
		// Nothing to do
	}
	
	/**
	 * Constructs a new {@code PageShown} object.
	 *
	 * @param pageClass The class of the page that is shown.
	 */
	public PageChange(String pageClass) {
	  this.pageClass = pageClass;
  }

  /**
   * Returns the class of the page that is shown.
   *
   * @return the page class.
   */
	public String getPageClass() {
		return pageClass;
	}
}
