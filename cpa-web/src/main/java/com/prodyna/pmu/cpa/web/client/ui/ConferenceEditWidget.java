/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.common.client.util.LogUtil;
import org.jboss.errai.databinding.client.BindableListWrapper;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;

import com.github.gwtbootstrap.client.ui.Form.SubmitEvent;
import com.github.gwtbootstrap.client.ui.SubmitButton;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.prodyna.pmu.cpa.web.client.ClientEntryPoint.ApplicationRuntime;
import com.prodyna.pmu.cpa.web.shared.PortableConference;
import com.prodyna.pmu.cpa.web.shared.PortableTalk;
import com.prodyna.pmu.cpa.web.shared.event.AdminModeChange;
import com.prodyna.pmu.cpa.web.shared.rest.ConferenceRestService;
import com.prodyna.pmu.cpa.web.shared.rest.TalkRestService;

/**
 * Editor widget for a conference object.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Dependent
public class ConferenceEditWidget extends Composite implements HasModel<PortableConference> {

	/** The UiBinder for this widget. */
	interface UiBinderInstance extends UiBinder<Widget, ConferenceEditWidget> {
		// Nothing to do
	}
  private static UiBinderInstance uiBinder = GWT.create(UiBinderInstance.class);
	
  /** The application runtime singleton. */
  @Inject
  private ApplicationRuntime rt;
  
  /** The service proxy. */
  @Inject
  private Caller<ConferenceRestService> service;
  
  @Inject
  private Caller<TalkRestService> serviceForTalks;
  
	/** Automatic binder for data fields. */
	@Inject @AutoBound
  private DataBinder<PortableConference> dataBinder;
	
	/** The talks list widget. */
	@Inject
	private ListWidget<PortableTalk, SmallListWidget.PortableTalkImpl> talks;

	/** Data field for the 'objectId' property. */
	@UiField @Bound
	protected TextBox objectId;
	
	/** Data field for the 'name' property. */
	@UiField @Bound
	protected TextBox name;

	/** Data field for the 'description' property. */
	@UiField @Bound
	protected TextArea description;
	
	/** Data field for the 'beginDate' property. */
	@UiField @Bound
	protected DateBox beginDate;
	
	/** Data field for the 'endDate' property. */
	@UiField @Bound 
	protected DateBox endDate;
	
	/** Container for the talks list. */
	@UiField 
	protected SimplePanel talksContainer;
	
	/** The save button. */
	@UiField
	protected SubmitButton buttonSave;

	/**
	 * Constructs a new {@code ConferenceListWidget} object.
	 */
	public ConferenceEditWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		LogUtil.log("ConferenceEditWidget constructed and bound");
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
  	resolveTalks();
  }

  protected void resolveTalks() {
  	PortableConference model = getModel();
  	final BindableListWrapper<PortableTalk> list = new BindableListWrapper<PortableTalk>(
  			new ArrayList<PortableTalk>()
  	);
		talks.setItems(list);
  	if (model != null && !model.getTalks().isEmpty()) {
  		for (String objectId : model.getTalks()) {
  			serviceForTalks.call(new RemoteCallback<PortableTalk>() {
					@Override public void callback(PortableTalk response) {
	          list.add(response);
          }
  			}).read(objectId);
  		}
  	}
  }
  
  /**
   * Click handler for the form's 'Cancel' button.
   *
   * @param event The click event.
   */
  @UiHandler("buttonCancel")
  protected void onClickCancel(ClickEvent event) {
  	History.back();
  }
  
  /**
   * Submit handler for the form.
   *
   * @param event The submit event.
   */
  @UiHandler("form")
  protected void onSubmit(SubmitEvent event) {
  	ConferenceRestService proxy = service.call(new RemoteCallback<PortableConference>() {
			@Override public void callback(PortableConference response) {
	      setModel(response);
      }
  	});
  	PortableConference model = getModel();
  	if (model.getObjectId() != null) {
  		proxy.update(model.getObjectId(), model);
  	}
  	else {
  		proxy.create(model);
  	}
  	event.cancel();
  }

	/**
	 * Updates the state of this widget.
	 */
	protected void update() {
  	boolean adminMode = rt.isAdminModeEnabled();
  	name.setReadOnly(!adminMode);
  	description.setReadOnly(!adminMode);
  	beginDate.setReadOnly(!adminMode);
  	endDate.setReadOnly(!adminMode);
  	buttonSave.setEnabled(adminMode);
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
		talksContainer.setWidget(talks);
		// Update widget
		update();
	}
}
