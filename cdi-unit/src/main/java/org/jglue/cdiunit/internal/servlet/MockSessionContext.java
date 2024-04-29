/*
 *    Copyright 2014 Bryn Cooke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jglue.cdiunit.internal.servlet;

import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.Vector;

import jakarta.enterprise.context.spi.Contextual;
import jakarta.enterprise.context.spi.CreationalContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.jboss.weld.context.http.HttpSessionContext;

/**
 * Mock implementation of <code>HttpSessionContext</code>.
 */
public class MockSessionContext implements HttpSessionContext
{
    public Enumeration getIds()
    {
        return new Vector().elements();
    }

    public HttpSession getSession(String arg0)
    {
        return null;
    }

	@Override
	public void activate() {

	}

	@Override
	public void deactivate() {

	}

	@Override
	public void invalidate() {
	}

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	public boolean destroy(HttpSession httpSession) {
		return false;
	}

	@Override
	public boolean associate(HttpServletRequest httpServletRequest) {
		return false;
	}

	@Override
	public boolean dissociate(HttpServletRequest httpServletRequest) {
		return false;
	}

	@Override
	public void destroy(Contextual<?> contextual) {

	}

	@Override
	public Class<? extends Annotation> getScope() {
		return null;
	}

	@Override
	public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
		return null;
	}

	@Override
	public <T> T get(Contextual<T> contextual) {
		return null;
	}

	@Override
	public boolean isActive() {
		return false;
	}
}
