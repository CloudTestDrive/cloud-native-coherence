package com.oracle.labs.helidon.coherence.charge.common.resources;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import io.helidon.security.abac.role.RoleValidator.Roles;
import io.helidon.security.annotations.Authenticated;

@Path("/stats")
@Authenticated
public interface CoherenceReportRESTResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/entries")
	@Roles("admin")
	@Operation(summary = "Gets billing entries count", description = "Returns the number of users in the billing cache")
	public Integer getEntriesCount();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/data")
	@Roles("admin")
	@Operation(summary = "Gets billing data", description = "Returns the billing cache map")
	public Map<String, Double> getData();
}
