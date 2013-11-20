/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui;

import javax.enterprise.context.Dependent;

import com.prodyna.pmu.cpa.web.shared.PortableSpeaker;

/**
 * TODO Comment on type 
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Dependent
public class SpeakerListWidget extends AbstractListWidget<PortableSpeaker> {

	/**
	 * Constructs a new {@code RoomListWidget} object.
	 */
  public SpeakerListWidget() {
	  super();
  }

	/**
	 * Constructs a new {@code RoomListWidget} object.
	 *
	 * @param model The model to set.
	 */
  public SpeakerListWidget(PortableSpeaker model) {
	  super(model);
  }
}
