/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.Iterables;
import com.prodyna.pmu.cpa.domain.Conference;

/**
 * TODO Comment on type 
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 */
@RunWith(Arquillian.class)
public class ConferenceServiceTestImpl {

	/**
	 * The ShrinkWrap deployment.
	 * 
	 * @return the deployment artifact.
	 */
	@Deployment
	public static Archive<?> deploy() {
		return ShrinkWrap.create(WebArchive.class, "cpa-ejb-impl-test.war")
				.addAsLibraries(
						Maven.resolver().loadPomFromClassLoaderResource("pom.xml")
								.importCompileAndRuntimeDependencies()
								.resolve().withTransitivity().asFile()
				)
				.addPackages(true, "com.prodyna.pmu.cpa")
				.addAsWebInfResource("META-INF/beans.xml", "beans.xml")
				;
	}

	/** The service bean to test. */
	@Inject
	private ConferenceServiceBean service;
	
	/**
	 * Tests the {@code list()} method. 
	 */
	@Test @InSequence(1)	
	public void testList() {
		Iterable<Conference> result = service.list();
		Assert.assertTrue(Iterables.size(result) == 0);
	}
}
