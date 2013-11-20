/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.pca.web.rest.impl;

import java.util.List;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.prodyna.pmu.cpa.domain.Talk;
import com.prodyna.pmu.cpa.ejb.TalkService;
import com.prodyna.pmu.cpa.web.rest.TalkRestService;

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
	public List<Talk> list() {
		return Lists.newArrayList(service.list());
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
