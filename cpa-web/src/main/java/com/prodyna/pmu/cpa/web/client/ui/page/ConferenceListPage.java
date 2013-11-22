/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui.page;

import java.util.List;

import javax.inject.Inject;

import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.prodyna.pmu.cpa.web.client.ui.ConferenceListWidget;
import com.prodyna.pmu.cpa.web.shared.domain.PortableConference;
import com.prodyna.pmu.cpa.web.shared.rest.ConferenceRestService;

/**
 * The page that lists all conferences.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Page(path="conferences", role=DefaultPage.class)
@Templated("Template.html#list")
public class ConferenceListPage extends AbstractListPage<PortableConference, ConferenceListWidget> {

	/** Transition to the edit page for a new object. */
	@Inject
	private TransitionTo<ConferenceEditPage> transitionToNew;
	
	/** The service proxy. */
	@Inject
	private Caller<ConferenceRestService> service;
	
	/**
	 * Constructs a new {@code ConferencePage} object.
	 */
	public ConferenceListPage() {
		// Nothing to do
	}

	@Override
  protected void load() {
		service.call(new RemoteCallback<List<PortableConference>>() {
			@Override public void callback(List<PortableConference> response) {
				setItems(response);
      }
		}).list();
  }

	@Override
  protected TransitionTo<?> transitionToNew() {
	  return transitionToNew;
  }
}
