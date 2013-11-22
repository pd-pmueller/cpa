/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.server.rest;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.prodyna.pmu.cpa.ejb.ConferenceServiceBean;
import com.prodyna.pmu.cpa.ejb.RoomServiceBean;
import com.prodyna.pmu.cpa.ejb.SpeakerServiceBean;
import com.prodyna.pmu.cpa.ejb.TalkServiceBean;

/**
 * Activator for JAX-RS. 
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {
	
	/** The {@link ConferenceServiceBean} implementation to expose. */
	private @Inject ConferenceServiceBean conferenceServiceBean;
	
	/** The {@link RoomServiceBean} implementation to expose. */
	private @Inject RoomServiceBean roomServiceBean;
	
	/** The {@link SpeakerServiceBean} implementation to expose. */
	private @Inject SpeakerServiceBean speakerServiceBean;
	
	/** The {@link TalkServiceBean} implementation to expose. */
	private @Inject TalkServiceBean talkServiceBean;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
  public Set<Object> getSingletons() {
		HashSet<Object> result = new HashSet<Object>();
		result.add(conferenceServiceBean);
		result.add(roomServiceBean);
		result.add(speakerServiceBean);
		result.add(talkServiceBean);
		return result;
  }
}
