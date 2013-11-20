/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui.page;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShown;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.prodyna.pmu.cpa.web.client.ClientEntryPoint.ApplicationRuntime;
import com.prodyna.pmu.cpa.web.client.ui.ConferenceListWidget;
import com.prodyna.pmu.cpa.web.shared.PortableConference;
import com.prodyna.pmu.cpa.web.shared.event.AdminModeChange;
import com.prodyna.pmu.cpa.web.shared.event.PageChange;
import com.prodyna.pmu.cpa.web.shared.rest.ConferenceRestService;

/**
 * The page that lists all conferences.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Page(path="conferences", role=DefaultPage.class)
@Templated("Conference.html#list")
public class ConferenceListPage extends Composite {

  /** The application runtime singleton. */
  @Inject
  private ApplicationRuntime rt;
  
	/** The model data binder. */
	@Inject @DataField
	private ListWidget<PortableConference, ConferenceListWidget> widget;
	
	/** The 'New' button. */
	@DataField
	private Button buttonNew = new Button("New", IconType.ASTERISK);
	
	/** Transition to the edit page for a new object. */
	@Inject
	private TransitionTo<ConferenceEditPage> transitionToEdit;
	
	/** The event bus for {@link PageChange} events. */
	@Inject
	private Event<PageChange> pageChangeEvent;
	
	/** The service proxy. */
	@Inject
	private Caller<ConferenceRestService> service;
	
	/**
	 * Constructs a new {@code ConferencePage} object.
	 */
	public ConferenceListPage() {
		// Nothing to do
	}

	/**
	 * Updates the state of this widget.
	 */
	protected void update() {
  	buttonNew.setEnabled(rt.isAdminModeEnabled());
	}
	
	/**
	 * Refreshes the contents of this page.
	 */
	@PageShown
	private void pageShown() {
		// Raise event
		pageChangeEvent.fire(new PageChange(ConferenceListPage.class.getName()));
		// Load
		service.call(new RemoteCallback<List<PortableConference>>() {
			@Override public void callback(List<PortableConference> response) {
				setItems(response);
      }
		}).list();
		// Update widgets
		update();
	}
	
	/**
	 * Sets the items to display.
	 *
	 * @param items The items to set.
	 */
	protected void setItems(List<PortableConference> items) {
		widget.setItems(items);
	}

  /**
   * Event observer for {@link AdminModeChange} events.
   *
   * @param event The event that was observed.
   */
  @SuppressWarnings("unused")
  private void onAdminModeChange(@Observes AdminModeChange event) {
  	update();
  }

	/**
	 * Initializes this page.
	 */
	@PostConstruct
	private void setup() {
		buttonNew.addClickHandler(new ClickHandler() {
			@Override public void onClick(ClickEvent event) {
				transitionToEdit.go(PageStates.empty());
			}
		});
	}
}
