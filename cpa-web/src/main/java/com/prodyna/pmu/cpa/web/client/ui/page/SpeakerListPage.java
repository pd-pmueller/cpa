/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui.page;

import java.util.List;

import javax.inject.Inject;

import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.prodyna.pmu.cpa.web.client.ui.SpeakerListWidget;
import com.prodyna.pmu.cpa.web.shared.domain.PortableSpeaker;
import com.prodyna.pmu.cpa.web.shared.rest.SpeakerRestService;

/**
 * The page that lists all talks.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Page(path="speakers")
@Templated("Template.html#list")
public class SpeakerListPage extends AbstractListPage<PortableSpeaker, SpeakerListWidget> {

	/** The service proxy. */
	@Inject
	private Caller<SpeakerRestService> service;
	
	/**
	 * Constructs a new {@code SpeakerListPage} object.
	 */
	public SpeakerListPage() {
		// Nothing to do
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
  protected void load() {
		service.call(new RemoteCallback<List<PortableSpeaker>>() {
			@Override public void callback(List<PortableSpeaker> response) {
				setItems(response);
      }
		}).list();
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  protected TransitionTo<?> transitionToNew() {
	  return null;
  }
}
