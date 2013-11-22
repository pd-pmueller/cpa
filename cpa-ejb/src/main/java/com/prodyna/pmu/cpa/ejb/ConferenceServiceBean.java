/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import javax.ws.rs.Path;

import com.prodyna.pmu.cpa.domain.Conference;

/**
 * EJB service interface for the {@link Conference} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Path("conference")
public interface ConferenceServiceBean extends EntityServiceBean.Listable<Conference> {
	
	// No additional methods
}
