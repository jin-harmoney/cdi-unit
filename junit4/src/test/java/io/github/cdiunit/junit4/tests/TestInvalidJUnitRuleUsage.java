/*
 * Copyright 2020 the original author or authors.
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
package io.github.cdiunit.junit4.tests;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import io.github.cdiunit.internal.TestConfiguration;
import io.github.cdiunit.internal.WeldHelper;
import io.github.cdiunit.junit4.internal.InvalidRuleFieldUsageException;

public class TestInvalidJUnitRuleUsage {

    @Rule
    public final TestName testName = new TestName();

    @Test(expected = InvalidRuleFieldUsageException.class)
    public void test() throws IOException {
        WeldHelper.configureWeld(new TestConfiguration(TestInvalidJUnitRuleUsage.class));
    }

}