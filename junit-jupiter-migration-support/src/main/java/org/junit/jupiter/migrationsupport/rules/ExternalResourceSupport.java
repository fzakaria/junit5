/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.jupiter.migrationsupport.rules;

import static org.junit.platform.commons.meta.API.Usage.Experimental;

import java.util.function.Function;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.junit.jupiter.migrationsupport.rules.adapter.AbstractTestRuleAdapter;
import org.junit.jupiter.migrationsupport.rules.adapter.ExternalResourceAdapter;
import org.junit.jupiter.migrationsupport.rules.member.RuleAnnotatedMember;
import org.junit.platform.commons.meta.API;

@API(Experimental)
public class ExternalResourceSupport implements BeforeEachCallback, AfterEachCallback {

	private final Function<RuleAnnotatedMember, AbstractTestRuleAdapter> adapterGenerator = ExternalResourceAdapter::new;

	private final AbstractTestRuleSupport fieldSupport = new TestRuleFieldSupport(this.adapterGenerator);
	private final AbstractTestRuleSupport methodSupport = new TestRuleMethodSupport(this.adapterGenerator);

	@Override
	public void beforeEach(TestExtensionContext context) throws Exception {
		this.fieldSupport.beforeEach(context);
		this.methodSupport.beforeEach(context);
	}

	@Override
	public void afterEach(TestExtensionContext context) throws Exception {
		this.fieldSupport.afterEach(context);
		this.methodSupport.afterEach(context);
	}

}
