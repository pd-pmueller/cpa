/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui;

import javax.enterprise.context.Dependent;

import com.prodyna.pmu.cpa.web.shared.domain.PortableRoom;

/**
 * Simple widget to show a {@link PortableRoom} on a list page.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Dependent
public class RoomListWidget extends AbstractListWidget<PortableRoom> {

	/**
	 * Constructs a new {@code RoomListWidget} object.
	 */
  public RoomListWidget() {
	  super();
  }

	/**
	 * Constructs a new {@code RoomListWidget} object.
	 *
	 * @param model The model to set.
	 */
  public RoomListWidget(PortableRoom model) {
	  super(model);
  }
}
