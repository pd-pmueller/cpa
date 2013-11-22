/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui.page;

import javax.inject.Inject;

import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShowing;
import org.jboss.errai.ui.nav.client.local.PageState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.common.base.Objects;
import com.google.gwt.user.client.ui.Composite;
import com.prodyna.pmu.cpa.web.client.ui.ConferenceEditWidget;
import com.prodyna.pmu.cpa.web.shared.domain.PortableConference;
import com.prodyna.pmu.cpa.web.shared.rest.ConferenceRestService;

/**
 * TODO Comment on type 
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Page(path="conference")
@Templated("Conference.html#edit")
public class ConferenceEditPage extends Composite {

	/** The page state. */
	@PageState
	private String objectId;
	
	/** The details widget. */
	@Inject @DataField
	private ConferenceEditWidget widget;

	/** The service proxy. */
	@Inject
	private Caller<ConferenceRestService> service;
	
	/**
	 * (Re-)initializes this widget when it is shown.
	 */
	@PageShowing
	private void setup() {
		PortableConference last = widget.getModel();
		if (last != null && (Objects.equal(last.getObjectId(), objectId)))
			return;
		// Set model
		if (objectId != null) {
			// From server-side
  		service.call(new RemoteCallback<PortableConference>() {
  			@Override public void callback(PortableConference response) {
  				widget.setModel(response);
        }
  		}).read(objectId);
		}
		else {
			// New instance
			widget.setModel(new PortableConference());
		}
	}
}
