/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui;

import javax.enterprise.context.Dependent;

import com.prodyna.pmu.cpa.web.shared.domain.PortableTalk;

/**
 * Simple widget to show a {@link PortableTalk} on a list page.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Dependent
public class TalkListWidget extends AbstractListWidget<PortableTalk> {

	/**
	 * Constructs a new {@code TalkListWidget} object.
	 */
  public TalkListWidget() {
	  super();
  }

	/**
	 * Constructs a new {@code TalkListWidget} object.
	 *
	 * @param model The model to set.
	 */
  public TalkListWidget(PortableTalk model) {
	  super(model);
  }
}
