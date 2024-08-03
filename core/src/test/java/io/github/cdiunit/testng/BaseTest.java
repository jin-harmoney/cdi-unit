/*
 * Copyright 2024 the original author or authors.
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
package io.github.cdiunit.testng;

import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;

import org.testng.annotations.BeforeClass;

import io.github.cdiunit.NgCdiRunner;

import static org.assertj.core.api.Assertions.assertThat;

abstract class BaseTest {

    @Inject
    private BeanManager beanManager;

    public BeanManager getBeanManager() {
        return beanManager;
    }

    @BeforeClass
    void failIfExtendsRunner() {
        assertThat(NgCdiRunner.class.isInstance(this))
                .withFailMessage(String.format("%s MUST NOT extend %s", this.getClass(), NgCdiRunner.class)).isFalse();
    }

}
