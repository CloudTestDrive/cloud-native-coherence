package com.oracle.labs.helidon.coherence.clients.test.grpc.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.oracle.labs.helidon.coherence.charge.common.data.BillingCost;
import com.oracle.labs.helidon.coherence.charge.common.resources.CoherenceChargeGRPCResource;

import io.helidon.microprofile.grpc.client.GrpcChannel;
import io.helidon.microprofile.grpc.client.GrpcProxy;
import io.helidon.security.annotations.Authenticated;
import lombok.extern.slf4j.Slf4j;

@Path("/charge")
@Authenticated
@ApplicationScoped
@Slf4j
public class CoherenceChargeGRPCTestResource {
	// the gRPC connection
	@Inject
	@GrpcProxy
	@GrpcChannel(name = "chargeserver")
	CoherenceChargeGRPCResource coherenceChargeGRPCResource;

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BillingCost updateCharges(BillingCost billingCost) {
		log.info("CoherenceChargeGRPCResourceTest forwarding billing request " + billingCost);
		log.info("coherenceChargeGRPCResource == null is " + (coherenceChargeGRPCResource == null));
		BillingCost bc = coherenceChargeGRPCResource.updateCharges(billingCost);
		log.info("CoherenceChargeGRPCResourceTest forwarding response " + bc);
		return bc;
	}
}
