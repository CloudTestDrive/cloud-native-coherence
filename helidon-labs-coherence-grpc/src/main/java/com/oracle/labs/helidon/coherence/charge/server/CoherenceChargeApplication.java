/*
 * Copyright (c) 2019, 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oracle.labs.helidon.coherence.charge.server;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import com.oracle.labs.helidon.coherence.charge.server.resources.CoherenceChargeGRPCResourceImpl;
import com.oracle.labs.helidon.coherence.charge.server.resources.CoherenceChargeRESTResourceImpl;
import com.oracle.labs.helidon.coherence.charge.server.resources.CoherenceReportRESTResourceImpl;
import com.oracle.labs.helidon.coherence.charge.server.resources.StatusResource;

/**
 * Simple Application that produces a greeting message.
 */
@ApplicationScoped
@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(title = "CoherenceGRPCApplication", description = "Provides a simple billing update engine", version = "0.0.1"))

public class CoherenceChargeApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		// here we have the classes to operate on
		return Set.of(CoherenceChargeRESTResourceImpl.class, CoherenceChargeGRPCResourceImpl.class,
				CoherenceReportRESTResourceImpl.class, StatusResource.class);
	}
}
