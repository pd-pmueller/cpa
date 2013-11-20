/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.prodyna.pmu.cpa.web.client.ClientEntryPoint.ApplicationRuntime;
import com.prodyna.pmu.cpa.web.shared.PortableObject;
import com.prodyna.pmu.cpa.web.shared.event.AdminModeChange;

/**
 * TODO Comment on type 
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <T> The model type.
 */
public class AbstractListWidget<T extends PortableObject> extends Composite implements HasModel<T> {

	/** The UiBinder for this widget. */
	interface UiBinderInstance extends UiBinder<Widget, AbstractListWidget<?>> {
		// Nothing to do
	}
  private static UiBinderInstance uiBinder = GWT.create(UiBinderInstance.class);
  
  /** The application runtime singleton. */
	@Inject
	private ApplicationRuntime rt;
	
	/** Automatic binder for data fields. */
	@Inject @AutoBound
  private DataBinder<T> dataBinder;
	
	/** Data field for the 'name' property. */
	@UiField @Bound
	protected InlineLabel name;

	/** The 'details' button. */
	@UiField
	protected Button buttonDetails;
	
	/** The 'delete' button. */
	@UiField
	protected Button buttonDelete;
	
	/**
	 * Constructs a new {@code RoomListWidget} object.
	 */
	public AbstractListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/**
	 * Constructs a new {@code RoomListWidget} object.
	 * 
	 * @param model The model object to load.
	 */
	public AbstractListWidget(T model) {
		this();
		setModel(model);
	}

	/**
	 * {@inheritDoc}
	 */
  @Override
  public T getModel() {
	  return (dataBinder != null) ? dataBinder.getModel() : null;
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public void setModel(T model) {
  	dataBinder.setModel(model, InitialState.FROM_MODEL);
  }

	/**
	 * Updates the state of this widget.
	 */
	protected void update() {
		// Enable or disable buttons
  	buttonDelete.setEnabled(rt.isAdminModeEnabled());
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
   * Initializes this widget.
   */
	@PostConstruct
	private void setup() {
		// Update widget
		update();
	}
}
