package com.oracle.labs.helidon.coherence.charge.common.resources;

import com.oracle.labs.helidon.coherence.charge.common.data.BillingCost;

import io.helidon.microprofile.grpc.core.Grpc;
import io.helidon.microprofile.grpc.core.Unary;

@Grpc(name = "charge")
//@ApplicationScoped
public interface CoherenceChargeGRPCResource {
	@Unary(name = "update")
	public BillingCost updateCharges(BillingCost billingCost);
}
