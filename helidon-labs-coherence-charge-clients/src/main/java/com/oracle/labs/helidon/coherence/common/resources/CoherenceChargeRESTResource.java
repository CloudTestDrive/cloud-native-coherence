package com.oracle.labs.helidon.coherence.common.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.oracle.labs.helidon.coherence.common.data.BillingCost;

import io.helidon.security.annotations.Authenticated;

@Path("/charge")
@Authenticated
@ApplicationScoped
public interface CoherenceChargeRESTResource {
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Updates a users charges", description = "Updates the cost charges for a specific user")
	public BillingCost updateCharges(
			@RequestBody(description = "The details of the billing entry", required = true, content = @Content(schema = @Schema(implementation = BillingCost.class, example = "{\"user\" : \"Fred\", \"charge\" : 0.5}"))) BillingCost billingCost);
}
