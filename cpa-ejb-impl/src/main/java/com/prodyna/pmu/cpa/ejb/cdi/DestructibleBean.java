/*
 * $Id$
 * Copyright 2013 PRODYNA AG
 */
package com.prodyna.pmu.cpa.ejb.cdi;

import java.lang.annotation.Annotation;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.common.base.Throwables;

/**
 * Helper class for manual lookup of beans via the JNDI {@link BeanManager}.
 *
 * @author <a href="mailto:pmueller@prodyna.com">pmueller@prodyna.com</a>
 * @param <T> The type of bean.
 */
public class DestructibleBean<T> {

	// TODO XXX To prevent memory leaks, a new CreationalContext should be established for each(?) bean
	
	/**
	 * Returns a bean instance for the specified type and qualifiers.
	 * <p>
	 * If no such bean could be found, a {@link RuntimeException} is raised.
	 *
	 * @param type The type of bean to return.
	 * @param qualifiers The bean qualifiers.
	 * @return a corresponding bean.
	 * @throws RuntimeException if a bean for the specified type and qualifiers could not be resolved.
	 */
  @SuppressWarnings("unchecked")
  public static <T> DestructibleBean<T> resolve(final Class<T> type, final Annotation... qualifiers) {
  	BeanManager beanManager = getBeanManager();
  	// Resolve
		DestructibleBean<T> result = null;
		Bean<T> bean = (Bean<T>) beanManager.resolve(beanManager.getBeans(type, qualifiers));
		if (bean != null) {
			CreationalContext<T> creationalContext = beanManager.createCreationalContext(bean);
			if (creationalContext != null) {
				T instance = bean.create(creationalContext);
				result = new DestructibleBean<T>(instance, bean, creationalContext);
			}
		}
		else {
			throw new RuntimeException("No bean of type " + type.getName() + " found in BeanManager");
		}
  	// Return
		return result;
  }
  
  /**
   * Returns the current {@link BeanManager} looked up via {@code JNDI}.
   *
   * @return a {@link BeanManager} instance.
   * @throws RuntimeException if no {@link BeanManager} could be obtained.
   */
  private static BeanManager getBeanManager() {
  	try {
    	InitialContext ic = new InitialContext();
    	BeanManager instance = (BeanManager) ic.lookup("java:comp/" + BeanManager.class.getSimpleName());
    	if (instance == null) {
    		throw new RuntimeException("BeanManager could not be obtained from context");
    	}
    	return instance;
  	}
  	catch (NamingException e) {
  		throw Throwables.propagate(e);
  	}
  }

  /** The bean instance. */
  private T instance;
  
  /** The bean's descriptor. */
  private Bean<T> bean;
  
  /** The bean's creational context. */
  private CreationalContext<T> context;

  /**
   * Constructs a new {@code DestructibleBean} object.
   *
   * @param instance The bean instance. 
   * @param bean The bean's descriptor.
   * @param context The bean's creational context.
   */
  protected DestructibleBean(T instance, Bean<T> bean, CreationalContext<T> context) {
      this.instance = instance;
      this.bean = bean;
      this.context = context;
  }

  /**
   * Returns the bean instance.
   *
   * @return the instance.
   */
  public T getInstance() {
    return instance;
  }    

  /**
   * Destroys the underlying bean instance.
   * <p>
   * The bean instance is not guaranteed to be still usable afterwards.
   */
  public void destroy() {
    bean.destroy(instance, context);
  }
}
