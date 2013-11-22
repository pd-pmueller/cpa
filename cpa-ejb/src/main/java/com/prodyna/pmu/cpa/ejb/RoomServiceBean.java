/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import javax.ws.rs.Path;

import com.prodyna.pmu.cpa.domain.Room;

/**
 * EJB service interface for the {@link Room} entity.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@Path("room")
public interface RoomServiceBean extends EntityServiceBean.Listable<Room> {
	
	// No additional methods
}
