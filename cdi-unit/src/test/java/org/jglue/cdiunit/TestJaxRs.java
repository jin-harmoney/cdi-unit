package org.jglue.cdiunit;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Providers;

import org.jglue.cdiunit.jaxrs.SupportJaxRs;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@SupportJaxRs
public class TestJaxRs {

	@Inject
	private WebService webService;

	@Test
	public void testJaxRs() {
		Assert.assertNotNull(webService.request);
		Assert.assertNotNull(webService.response);
		Assert.assertNotNull(webService.context);
		Assert.assertNotNull(webService.uriInfo);
		Assert.assertNotNull(webService.jaxRsRequest);
		Assert.assertNotNull(webService.securityContext);
		Assert.assertNotNull(webService.providers);
		Assert.assertNotNull(webService.headers);
	}

	@Test
	@InRequestScope
	public void testRequestAttributeAccess() {
		Assert.assertNull(webService.request.getAttribute("test"));
	}

	public static class WebService {
		@Context
		HttpServletRequest request;

		@Context
		HttpServletResponse response;

		@Context
		ServletContext context;

		@Context
		UriInfo uriInfo;

		@Context
		Request jaxRsRequest;

		@Context
		SecurityContext securityContext;

		@Context
		Providers providers;


		@Context
		HttpHeaders headers;

	}
}
