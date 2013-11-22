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

import com.prodyna.pmu.cpa.web.client.ui.TalkListWidget;
import com.prodyna.pmu.cpa.web.shared.domain.PortableTalk;
import com.prodyna.pmu.cpa.web.shared.rest.TalkRestService;

/**
 * The page that lists all talks.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Page(path="talks")
@Templated("Template.html#list")
public class TalkListPage extends AbstractListPage<PortableTalk, TalkListWidget> {

	/** The service proxy. */
	@Inject
	private Caller<TalkRestService> service;
	
	/**
	 * Constructs a new {@code TalkListPage} object.
	 */
	public TalkListPage() {
		// Nothing to do
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
  protected void load() {
		service.call(new RemoteCallback<List<PortableTalk>>() {
			@Override public void callback(List<PortableTalk> response) {
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
