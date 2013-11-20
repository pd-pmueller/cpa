/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.shared.event;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Event object that is raised whenever the admin mode is enabled or disabled.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Portable
public class AdminModeChange {

	/** Flag whether the admin mode was enabled or disabled. */
	private boolean adminModeEnabled;
	
	/**
	 * Constructs a new {@code AdminModeChange} object.
	 */
	public AdminModeChange() {
		// Nothing to do
	}
	
	/**
	 * Constructs a new {@code AdminModeChange} object.
	 *
	 * @param adminModeEnabled Flag whether the admin mode was enabled or disabled.
	 */
	public AdminModeChange(boolean adminModeEnabled) {
		this.adminModeEnabled = adminModeEnabled;
	}

	/**
	 * Returns if the admin mode was enabled or disabled.
	 *
	 * @return {@code true} if the admin mode was enabled; {@code false} if it was disabled.
	 */
	public boolean isAdminModeEnabled() {
		return adminModeEnabled;
	}
}
