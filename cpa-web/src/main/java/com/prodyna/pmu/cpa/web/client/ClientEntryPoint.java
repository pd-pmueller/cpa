/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.Navigation;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point for the application.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@EntryPoint
public class ClientEntryPoint {
	
	/** The root panel to add widgets to. */
	private @Inject RootPanel root;

	/** Errai's navigation. */
	private @Inject Navigation navigation;
	
	/**
	 * Constructs this entry point when the application is accessed via a browser.
	 */
	@PostConstruct
	private void construct() {
		DockPanel panel = new DockPanel();
    panel.add(navigation.getContentPanel(), DockPanel.CENTER);
    panel.setWidth("100%");
    root.add(panel);
	}
}
