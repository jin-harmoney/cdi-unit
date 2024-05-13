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
package org.jglue.cdiunit.internal.ejb;


import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.AnnotatedType;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessAnnotatedType;
import jakarta.enterprise.inject.spi.configurator.AnnotatedTypeConfigurator;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Inject;

import org.jglue.cdiunit.internal.ejb.EJbName.EJbNameLiteral;
import org.jglue.cdiunit.internal.ejb.EJbQualifier.EJbQualifierLiteral;

public class EjbExtension implements Extension {

	public <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {
		AnnotatedType<T> annotatedType = pat.getAnnotatedType();
		AnnotatedTypeConfigurator<T> typeConfigurator = pat.configureAnnotatedType();
		Stateless stateless = annotatedType.getAnnotation(Stateless.class);
		if (stateless != null) {
			processClass(typeConfigurator, stateless.name());
		}

		Stateful stateful = annotatedType.getAnnotation(Stateful.class);
		if (stateful != null) {
			processClass(typeConfigurator, stateful.name());
		}
		try {
			Singleton singleton = annotatedType.getAnnotation(Singleton.class);
			if (singleton != null) {
				processClass(typeConfigurator, singleton.name());
			}
		} catch (NoClassDefFoundError e) {
			// EJB 3.0
		}

		typeConfigurator.filterMethods(annotatedMethod -> annotatedMethod.getAnnotation(EJB.class) != null)
			.forEach(methodConfigurator -> {
				String beanName = methodConfigurator.getAnnotated().getAnnotation(EJB.class).beanName();

				methodConfigurator.add(EJbQualifierLiteral.INSTANCE);
				methodConfigurator.remove(a -> a.annotationType().equals(EJB.class));
				methodConfigurator.add( beanName.isEmpty() ? DefaultLiteral.INSTANCE : new EJbNameLiteral(beanName));
			});

		typeConfigurator.filterFields(annotatedField -> annotatedField.getAnnotation(EJB.class) != null)
			.forEach(fieldConfigurator -> {
				Produces produces = fieldConfigurator.getAnnotated().getAnnotation(Produces.class);
				if (produces == null) {
					fieldConfigurator.add(new AnnotationLiteral<Inject>(){private static final long serialVersionUID = 1L;});
				}

				String beanName = fieldConfigurator.getAnnotated().getAnnotation(EJB.class).beanName();
				fieldConfigurator.remove(a -> a.annotationType().equals(EJB.class));
				fieldConfigurator.add(EJbQualifierLiteral.INSTANCE);
				fieldConfigurator.add(beanName.isEmpty() ? DefaultLiteral.INSTANCE : new EJbNameLiteral(beanName));
			});
	}

	private static <T> void processClass(AnnotatedTypeConfigurator<T> typeConfigurator, String name ) {
		typeConfigurator.add(ApplicationScoped.Literal.INSTANCE);
		typeConfigurator.add(EJbQualifierLiteral.INSTANCE);
		typeConfigurator.add(name.isEmpty() ? DefaultLiteral.INSTANCE : new EJbNameLiteral(name));
	}
}
