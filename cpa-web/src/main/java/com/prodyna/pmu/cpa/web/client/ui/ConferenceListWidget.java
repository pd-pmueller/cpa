/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;

import com.github.gwtbootstrap.client.ui.Badge;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import com.prodyna.pmu.cpa.web.client.ClientEntryPoint.ApplicationRuntime;
import com.prodyna.pmu.cpa.web.client.ui.data.DateStringConverter;
import com.prodyna.pmu.cpa.web.client.ui.page.ConferenceEditPage;
import com.prodyna.pmu.cpa.web.client.ui.page.PageStates;
import com.prodyna.pmu.cpa.web.shared.domain.PortableConference;
import com.prodyna.pmu.cpa.web.shared.event.AdminModeChange;

/**
 * Widget that displays a single {@code conference} in a list.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Dependent
public class ConferenceListWidget extends Composite implements HasModel<PortableConference> {
	
	/** The UiBinder for this widget. */
	interface UiBinderInstance extends UiBinder<Widget, ConferenceListWidget> {
		// Nothing to do
	}
  private static UiBinderInstance uiBinder = GWT.create(UiBinderInstance.class);

  /** The application runtime singleton. */
	@Inject
	private ApplicationRuntime rt;
	
	/** Automatic binder for data fields. */
	@Inject @AutoBound
  private DataBinder<PortableConference> dataBinder;
	
	/** Data field for the 'name' property. */
	@UiField @Bound
	protected InlineLabel name;
	
	/** The talks count badge. */
	@UiField
	protected Badge talksCount;
	
	/** Data field for the 'beginDate' property. */
	@UiField @Bound(converter=DateStringConverter.class)
	protected InlineLabel beginDate;
	
	/** Data field for the 'endDate' property. */
	@UiField @Bound(converter=DateStringConverter.class) 
	protected InlineLabel endDate;
	
	/** The 'details' button. */
	@UiField
	protected Button buttonDetails;
	
	/** The 'delete' button. */
	@UiField
	protected Button buttonDelete;
	
	/** The page transition to the 'edit' page. */
	@Inject
	private TransitionTo<ConferenceEditPage> transitionToEdit;
	
	/**
	 * Constructs a new {@code ConferenceListWidget} object.
	 */
	public ConferenceListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/**
	 * Constructs a new {@code ConferenceListWidget} object.
	 * 
	 * @param model The model object to load.
	 */
	public ConferenceListWidget(PortableConference model) {
		this();
		setModel(model);
	}
	
	/**
	 * {@inheritDoc}
	 */
  @Override
  public PortableConference getModel() {
	  return (dataBinder != null) ? dataBinder.getModel() : null;
  }

	/**
	 * {@inheritDoc}
	 */
  @Override
  public void setModel(PortableConference model) {
  	dataBinder.setModel(model, InitialState.FROM_MODEL);
  }
  
	/**
	 * Handles the click event on the 'details' button.
	 *
	 * @param event The event object.
	 */
	@UiHandler("buttonDetails")
	protected void onClickButtonDetails(ClickEvent event) {
		transitionToEdit.go(PageStates.editObject(getModel().getObjectId()));
	}
	
	/**
	 * Handles the click event on the 'delete' button.
	 *
	 * @param event The event object.
	 */
	@UiHandler("buttonDelete")
	protected void onClickButtonDelete(ClickEvent event) {
		// TODO
	}
	
	/**
	 * Updates the state of this widget.
	 */
	protected void update() {
		// Enable or disable buttons
  	buttonDelete.setEnabled(rt.isAdminModeEnabled());
  	// Refresh unbound fields
  	PortableConference model = getModel();
  	if (model != null) {
  		talksCount.setText(Integer.toString(model.getTalks().size()));
  	}
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
