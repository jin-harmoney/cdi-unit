package org.jglue.cdiunit.tests.testng;

import javax.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;

public class ASuper {
	@Inject
	private BeanManager beanManager;

	public BeanManager getBeanManager() {
		return beanManager;
	}
}
