/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import com.prodyna.pmu.cpa.domain.Conference;

/**
 * EJB service interface for the {@link Conference} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public interface ConferenceService extends EntityServiceBean.Listable<Conference> {
	
	/**
	 * Schedules a talk for the specified conference.
	 *
	 * @param conference The conference to schedule the talk for.
	 * @param talk The talk to schedule.
	 */
	void schedule(String conference, String talk);
}
