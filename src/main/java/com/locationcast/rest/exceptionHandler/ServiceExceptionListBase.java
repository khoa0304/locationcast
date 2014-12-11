/**
 * 
 */
package com.locationcast.rest.exceptionHandler;

import java.util.ListResourceBundle;

/**
 * @author Khoa
 *
 */
public class ServiceExceptionListBase extends ListResourceBundle {


	@Override
	protected Object[][] getContents() {
		return exceptionList;
	}

	static final Object[][] exceptionList={
		
	};
}
