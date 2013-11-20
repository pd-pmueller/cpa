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
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.prodyna.pmu.cpa.web.client.ui.RoomListWidget;
import com.prodyna.pmu.cpa.web.shared.PortableRoom;
import com.prodyna.pmu.cpa.web.shared.rest.RoomRestService;

/**
 * The page that lists all rooms.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Page(path="rooms")
@Templated("Template.html#list")
public class RoomListPage extends AbstractListPage<PortableRoom, RoomListWidget> {

	/** The service proxy. */
	@Inject
	private Caller<RoomRestService> service;
	
	/**
	 * Constructs a new {@code RoomListPage} object.
	 */
	public RoomListPage() {
		// Nothing to do
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
  protected void load() {
		service.call(new RemoteCallback<List<PortableRoom>>() {
			@Override public void callback(List<PortableRoom> response) {
				setItems(response);
      }
		}).list();
  }
}
