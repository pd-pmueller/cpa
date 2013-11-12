/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import javax.ejb.Local;

import com.prodyna.pmu.cpa.Conference;

/**
 * REST interface for the {@link Conference} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Local
public interface ConferenceService extends EntityService.Listable<Conference> {
	
	// No additional methods supported
}
