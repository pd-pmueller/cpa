/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui.page;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Utility class for page state multimaps.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public final class PageStates {

  /**
   * Returns a new empty page state {@link Multimap}.
   *
   * @return a new map.
   */
  public static final Multimap<String, String> empty() {
  	return ArrayListMultimap.create();
  }
  
  /**
   * Returns the page state for an edit page.
   *
   * @param objectId The identifier of the object to edit.
   * @return the corresponding page state.
   */
  public static final Multimap<String, String> editObject(String objectId) {
  	Multimap<String, String> result = empty();
  	result.put("objectId", objectId);
  	return result;
  }
	
	/**
	 * Constructs a new {@code PageStates} object.
	 */
	private PageStates() {
		// Hidden
	}
}
