/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.Navigation;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.prodyna.pmu.cpa.web.client.ui.Menu;
import com.prodyna.pmu.cpa.web.shared.event.AdminModeChange;

/**
 * Entry point for the application.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@EntryPoint
public class ClientEntryPoint {
	
	/** Errai's navigation. */
	private @Inject Navigation navigation;
	
	/** The application layout panel. */
	private DockPanel layout = new DockPanel();

	/** The {@code Menu}. */
	@Inject 
	private Menu menu;

	/** Flag whether the application is in admin mode. */
	private boolean adminModeEnabled = true;
	
	/** Event bus for {@code AdminModeChange} events. */
	@Inject
	private Event<AdminModeChange> adminModeChangeEvent;
	
	
	/**
	 * Returns whether the application is in admin mode.
	 *
	 * @return {@code true} if the application is in admin mode; {@code false} otherwise.
	 */
	public boolean isAdminModeEnabled() {
		return adminModeEnabled;
	}

	/**
	 * Sets the admin mode for the application.
	 *
	 * @param adminModeEnabled {@code true} to set admin mode; {@code false} for unprivileged mode.
	 */
	public void setAdminModeEnabled(boolean adminModeEnabled) {
		if (this.adminModeEnabled != adminModeEnabled) {
			this.adminModeEnabled = adminModeEnabled;
			adminModeChangeEvent.fire(new AdminModeChange(adminModeEnabled));
		}
	}
	
	/**
	 * Constructs this entry point when the application is accessed via a browser.
	 */
	@PostConstruct
	private void construct() {
		// RestClient configuration
		RestClient.setApplicationRoot("/cpa-rest/rest");
		RestClient.setJacksonMarshallingActive(true);
		// UI setup
    layout.add(menu, DockPanel.NORTH);
    layout.add(navigation.getContentPanel(), DockPanel.CENTER);
    layout.setWidth("100%");
    layout.setHeight("100%");
    layout.setCellVerticalAlignment(menu, DockPanel.ALIGN_TOP);
    RootPanel.get().add(layout);
	}
}
