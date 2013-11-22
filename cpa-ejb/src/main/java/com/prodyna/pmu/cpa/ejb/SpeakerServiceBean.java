/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import javax.ws.rs.Path;

import com.prodyna.pmu.cpa.domain.Speaker;

/**
 * EJB service interface for the {@link Speaker} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Path("speaker")
public interface SpeakerServiceBean extends EntityServiceBean.Listable<Speaker> {
	
	// No additional methods
}
