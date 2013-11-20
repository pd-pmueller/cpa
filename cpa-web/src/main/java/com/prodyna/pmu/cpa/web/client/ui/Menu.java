/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.web.client.ui;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.errai.ui.nav.client.local.Navigation;

import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.prodyna.pmu.cpa.web.client.ClientEntryPoint.ApplicationRuntime;
import com.prodyna.pmu.cpa.web.client.ui.page.ConferenceListPage;
import com.prodyna.pmu.cpa.web.client.ui.page.PageStates;
import com.prodyna.pmu.cpa.web.client.ui.page.RoomListPage;
import com.prodyna.pmu.cpa.web.client.ui.page.SpeakerListPage;
import com.prodyna.pmu.cpa.web.client.ui.page.TalkListPage;
import com.prodyna.pmu.cpa.web.shared.event.AdminModeChange;
import com.prodyna.pmu.cpa.web.shared.event.PageChange;

/**
 * The application menu bar, partly static for now.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Dependent
public class Menu extends Composite {

	/** The UiBinder for this widget. */
	interface UiBinderInstance extends UiBinder<Widget, Menu> {
		// Nothing to do
	}
  private static UiBinderInstance uiBinder = GWT.create(UiBinderInstance.class);

  /** The application runtime singleton. */
	@Inject
	private ApplicationRuntime rt;
	
  /** The navigation singleton. */
  @Inject
  private Navigation navigation;

  /** The navigation link for the 'ConfereanceListPage'. */
  @UiField
  protected NavLink conferencesLink;

  /** The navigation link for the 'TalksListPage'. */
  @UiField
  protected NavLink talksLink;

  /** The navigation link for the 'SpeakersListPage'. */
  @UiField
  protected NavLink speakersLink;

  /** The navigation link for the 'RoomsListPage'. */
  @UiField
  protected NavLink roomsLink;
  
  /** The admin mode 'enable' link. */
  @UiField
  protected NavLink adminModeEnableLink;

  /** The admin mode 'disable' link. */
  @UiField
  protected NavLink adminModeDisableLink;
  
  /** The navigation link mapping. */
  private Map<String, NavLink> pageLinkMap = new HashMap<String, NavLink>();
  
  /** The currently active link, if any. */
  private NavLink activeLink = null;
  
	/**
	 * Constructs a new {@code ConferenceListWidget} object.
	 */
	public Menu() {
		initWidget(uiBinder.createAndBindUi(this));
	}

  /**
   * Event observer for {@link AdminModeChange} events.
   *
   * @param event The event that was observed.
   */
  protected void onAdminModeChange(@Observes AdminModeChange event) {
  	adminModeEnableLink.setDisabled(event.isAdminModeEnabled());
  	adminModeDisableLink.setDisabled(!event.isAdminModeEnabled());
  }
  
  /**
   * Event observer for {@link PageChange} events.
   *
   * @param event The event that was observed.
   */
  protected void onPageChangeEvent(@Observes PageChange event) {
  	NavLink link = pageLinkMap.get(event.getPageClass());
  	if (link != null) {
    	if (activeLink != null) {
    		activeLink.setActive(false);
    	}
  		link.setActive(true);
  		activeLink = link;
  	}
  }
	
	/**
	 * Handles click event from the 'conferencesLink' item.
	 *
	 * @param event The click event.
	 */
	@UiHandler("conferencesLink")
	protected void onClickConferencesLink(ClickEvent event) {
		navigation.goTo(ConferenceListPage.class, PageStates.empty());
	}

	/**
	 * Handles click event from the 'talksLink' item.
	 *
	 * @param event The click event.
	 */
	@UiHandler("talksLink")
	protected void onClickTalksLink(ClickEvent event) {
		navigation.goTo(TalkListPage.class, PageStates.empty());
	}

	/**
	 * Handles click event from the 'speakersLink' item.
	 *
	 * @param event The click event.
	 */
	@UiHandler("speakersLink")
	protected void onClickSpeakersLink(ClickEvent event) {
		navigation.goTo(SpeakerListPage.class, PageStates.empty());
	}

	/**
	 * Handles click event from the 'roomsLink' item.
	 *
	 * @param event The click event.
	 */
	@UiHandler("roomsLink")
	protected void onClickRoomsLink(ClickEvent event) {
		navigation.goTo(RoomListPage.class, PageStates.empty());
	}

	/**
	 * Handles click event from the 'roomsLink' item.
	 *
	 * @param event The click event.
	 */
	@UiHandler("adminModeEnableLink")
	protected void onClickAdminModeEnableLink(ClickEvent event) {
		rt.setAdminModeEnabled(true);
	}

	/**
	 * Handles click event from the 'roomsLink' item.
	 *
	 * @param event The click event.
	 */
	@UiHandler("adminModeDisableLink")
	protected void onClickAdminModeDisableLink(ClickEvent event) {
		rt.setAdminModeEnabled(false);
	}

	/**
   * Initializes this widget.
   */
	@PostConstruct
	private void setup() {
		// Enable or disable links
  	adminModeEnableLink.setDisabled(rt.isAdminModeEnabled());
  	adminModeDisableLink.setDisabled(!rt.isAdminModeEnabled());
  	// Create mapping
  	pageLinkMap.put(ConferenceListPage.class.getName(), conferencesLink);
  	pageLinkMap.put(TalkListPage.class.getName(), talksLink);
  	pageLinkMap.put(SpeakerListPage.class.getName(), speakersLink);
  	pageLinkMap.put(RoomListPage.class.getName(), roomsLink);
	}
}
