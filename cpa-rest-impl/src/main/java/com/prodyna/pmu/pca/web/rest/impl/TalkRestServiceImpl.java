/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.pca.web.rest.impl;

import javax.inject.Inject;

import com.prodyna.pmu.cpa.Talk;
import com.prodyna.pmu.cpa.ejb.TalkService;
import com.prodyna.pmu.pca.web.rest.TalkRestService;

/**
 * Implementation of the {@link TalkRestService} interface using the {@link TalkService} EJB.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
public class TalkRestServiceImpl implements TalkRestService {

	/** The service bean to use. */
	private @Inject TalkService service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Talk> list() {
		return service.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Talk read(String objectId) {
		return service.read(objectId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Talk create(Talk object) {
		return service.create(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Talk update(String objectId, Talk object) {
		return service.update(objectId, object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Talk delete(String objectId) {
		return service.delete(objectId);
	}
}
