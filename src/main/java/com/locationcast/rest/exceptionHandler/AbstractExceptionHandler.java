/**
 * 
 */
package com.locationcast.rest.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author Khoa
 *
 */
public abstract class AbstractExceptionHandler {

	@Autowired
	private ReloadableResourceBundleMessageSource resourceBundle;

	public ReloadableResourceBundleMessageSource getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(
			ReloadableResourceBundleMessageSource resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
}
