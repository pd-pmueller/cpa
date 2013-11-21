/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;

import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.prodyna.pmu.cpa.web.shared.PortableObject;
import com.prodyna.pmu.cpa.web.shared.PortableTalk;

/**
 * Widget for a small list of {@link PortableObject}s.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <T> The model type.
 */
public class SmallListWidget<T extends PortableObject> extends Composite implements HasModel<T> {

	@Dependent
	public static class PortableTalkImpl extends SmallListWidget<PortableTalk> {
    public PortableTalkImpl() {
	    super();
    }
    public PortableTalkImpl(PortableTalk model) {
	    super(model);
    }
	}
	
	/** The UiBinder for this widget. */
	interface UiBinderInstance extends UiBinder<Widget, SmallListWidget<?>> {
		// Nothing to do
	}
  private static UiBinderInstance uiBinder = GWT.create(UiBinderInstance.class);

	/** Automatic binder for data fields. */
	@Inject @AutoBound
  private DataBinder<T> dataBinder;
	
	/** Data field for the 'name' property. */
	@UiField @Bound
	protected Label name;

	/**
	 * Constructs a new {@code SmallListWidget} object.
	 */
	public SmallListWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Constructs a new {@code SmallListWidget} object.
	 * 
	 * @param model The model object to load.
	 */
	public SmallListWidget(T model) {
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
}
