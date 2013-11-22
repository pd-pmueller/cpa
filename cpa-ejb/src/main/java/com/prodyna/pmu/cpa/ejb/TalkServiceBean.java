/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import java.util.Date;

import javax.ws.rs.Path;

import com.prodyna.pmu.cpa.domain.Talk;

/**
 * EJB service interface for the {@link Talk} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Path("talk")
public interface TalkServiceBean extends EntityServiceBean.Listable<Talk> {
	
	/**
	 * Returns a list of talks held by the speaker with the specified object identifier.
	 *
	 * @param objectId The speaker's object identifier.
	 * @return a list of talks for this speaker.
	 */
	Iterable<Talk> forSpeaker(String objectId);
	
	/**
	 * Returns a list of talks held in the specified timeslot.
	 *
	 * @param min The minimum time.
	 * @param max The maximum time.
	 * @return a list of talks in the specified timeslot.
	 */
	Iterable<Talk> forTimeslot(Date min, Date max);
}
