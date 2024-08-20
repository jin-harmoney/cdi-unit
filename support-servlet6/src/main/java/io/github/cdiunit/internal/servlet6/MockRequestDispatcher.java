/*
 * Copyright 2014 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.cdiunit.internal.servlet6;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class MockRequestDispatcher implements RequestDispatcher {
    private ServletRequest forwardedRequest;
    private ServletResponse forwardedResponse;
    private ServletRequest includedRequest;
    private ServletResponse includedResponse;
    private String path;

    /**
     * Sets the path for this <code>RequestDispatcher</code>.
     *
     * @param path the path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Returns the name or path used to retrieve this <code>RequestDispatcher</code>.
     *
     * @return the name or path used to retrieve this <code>RequestDispatcher</code>
     */
    public String getPath() {
        return path;
    }

    public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        forwardedRequest = request;
        forwardedResponse = response;
    }

    public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        includedRequest = request;
        includedResponse = response;
    }

    public ServletRequest getForwardedRequest() {
        return forwardedRequest;
    }

    public ServletResponse getForwardedResponse() {
        return forwardedResponse;
    }

    public ServletRequest getIncludedRequest() {
        return includedRequest;
    }

    public ServletResponse getIncludedResponse() {
        return includedResponse;
    }
}
