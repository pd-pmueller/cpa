/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui;

import javax.enterprise.context.Dependent;

import com.prodyna.pmu.cpa.web.shared.domain.PortableSpeaker;

/**
 * Simple widget to show a {@link PortableSpeaker} on a list page.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Dependent
public class SpeakerListWidget extends AbstractListWidget<PortableSpeaker> {

	/**
	 * Constructs a new {@code SpeakerListWidget} object.
	 */
  public SpeakerListWidget() {
	  super();
  }

	/**
	 * Constructs a new {@code SpeakerListWidget} object.
	 *
	 * @param model The model to set.
	 */
  public SpeakerListWidget(PortableSpeaker model) {
	  super(model);
  }
}
