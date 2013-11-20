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

import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.nav.client.local.PageShown;
import org.jboss.errai.ui.shared.api.annotations.DataField;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.prodyna.pmu.cpa.web.client.ClientEntryPoint.ApplicationRuntime;
import com.prodyna.pmu.cpa.web.shared.PortableObject;
import com.prodyna.pmu.cpa.web.shared.event.AdminModeChange;
import com.prodyna.pmu.cpa.web.shared.event.PageChange;

/**
 * TODO Comment on type 
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <M> The model type.
 * @param <W> The list widget type.
 */
public abstract class AbstractListPage<M extends PortableObject, W extends HasModel<M> & IsWidget> extends Composite {

  /** The application runtime singleton. */
  @Inject
  private ApplicationRuntime rt;
  
	/** The model data binder. */
	@Inject @DataField
	private ListWidget<M, W> widget;
	
	/** The 'New' button. */
	@DataField
	private Button buttonNew = new Button("New", IconType.ASTERISK);
	
	/** The event bus for {@link PageChange} events. */
	@Inject
	private Event<PageChange> pageChangeEvent;
	
	/**
	 * Constructs a new {@code RoomListPage} object.
	 */
	public AbstractListPage() {
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
		pageChangeEvent.fire(new PageChange(getClass().getName()));
		// Load
		load();
		// Update widgets
		update();
	}
	
	/**
	 * Sets the items to display.
	 *
	 * @param items The items to set.
	 */
	protected void setItems(List<M> items) {
		widget.setItems(items);
	}
	
	/**
	 * Triggered when showing the page to load the object list.
	 */
	protected abstract void load();

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
				Window.alert("New!");
				// TODO
			}
		});
	}
}
