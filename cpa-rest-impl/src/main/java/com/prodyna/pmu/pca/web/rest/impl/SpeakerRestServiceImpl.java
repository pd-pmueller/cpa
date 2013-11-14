/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.pca.web.rest.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.prodyna.pmu.cpa.domain.Speaker;
import com.prodyna.pmu.cpa.ejb.SpeakerService;
import com.prodyna.pmu.pca.web.rest.SpeakerRestService;

/**
 * Implementation of the {@link SpeakerRestService} interface using the {@link SpeakerService} EJB.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@RequestScoped
public class SpeakerRestServiceImpl implements SpeakerRestService {

	/** The service bean to use. */
	private @Inject SpeakerService service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Speaker> list() {
		return service.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Speaker read(String objectId) {
		return service.read(objectId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Speaker create(Speaker object) {
		return service.create(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Speaker update(String objectId, Speaker object) {
		return service.update(objectId, object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Speaker delete(String objectId) {
		return service.delete(objectId);
	}
}
