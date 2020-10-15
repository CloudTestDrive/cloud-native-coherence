package com.oracle.labs.helidon.coherencegrpc.resources;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.oracle.labs.helidon.coherencegrpc.data.BillingCost;

import io.helidon.security.abac.role.RoleValidator.Roles;
import io.helidon.security.annotations.Authenticated;

@ApplicationScoped
@Path("/charge")
@Authenticated
public class CoherenceGRPCResource {
	private Map<String, Double> billingInfo = new HashMap<>();

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Updates a users charges", description = "Updates the cost charges for a specific user")
	public BillingCost updateCharges(
			@RequestBody(description = "The details of the billing entry", required = true, content = @Content(schema = @Schema(implementation = BillingCost.class, example = "{\"user\" : \"Fred\", \"charge\" : 0.5}"))) BillingCost billingCost) {
		Double oldCost = billingInfo.get(billingCost.getUser());
		Double newCost = 0.0;
		// update based on any existing value (if present)
		if (oldCost == null) {
			// not previously seen
			newCost = billingCost.getCharge();
		} else {
			newCost += billingCost.getCharge();
		}
		billingInfo.put(billingCost.getUser(), newCost);
		return new BillingCost(billingCost.getUser(), newCost);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/entries")
	@Roles("admin")
	@Operation(summary = "Gets billing entries count", description = "Returns the number of users in the billing cache")
	public Integer getEntriesCount() {
		return billingInfo.size();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/data")
	@Roles("admin")
	@Operation(summary = "Gets billing data", description = "Returns the billing cache map")
	public Map<String, Double> getData() {
		return billingInfo;
	}
}
