package com.oracle.labs.helidon.coherence.server.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.oracle.coherence.cdi.Name;
import com.oracle.labs.helidon.coherence.common.data.BillingCost;
import com.oracle.labs.helidon.coherence.common.resources.CoherenceChargeRESTResource;
import com.tangosol.net.NamedMap;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CoherenceChargeRESTResourceImpl implements CoherenceChargeRESTResource {
	@Inject
	@Name("Charges") // The name of the Map to setup.
	private NamedMap<String, Double> billingInfo;
	// private Map<String, Double> billingInfo = new HashMap<>() ;

	public BillingCost updateCharges(BillingCost billingCost) {
		log.info("CoherenceChargeRESTResourceImpl received billing request " + billingCost);
		Double oldCost = billingInfo.get(billingCost.getUser());
		Double newCost;
		// update based on any existing value (if present)
		if (oldCost == null) {
			// not previously seen
			newCost = billingCost.getCharge();
		} else {
			newCost = oldCost + billingCost.getCharge();
		}
		billingInfo.put(billingCost.getUser(), newCost);
		return new BillingCost(billingCost.getUser(), newCost);
	}
}
