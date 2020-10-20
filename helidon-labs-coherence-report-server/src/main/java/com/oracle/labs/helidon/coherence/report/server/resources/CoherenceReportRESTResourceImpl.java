package com.oracle.labs.helidon.coherence.report.server.resources;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.oracle.coherence.cdi.Name;
import com.oracle.labs.helidon.coherence.report.common.resources.CoherenceReportRESTResource;
import com.tangosol.net.NamedMap;

@ApplicationScoped
public class CoherenceReportRESTResourceImpl implements CoherenceReportRESTResource {
	@Inject
	@Name("Charges") // The name of the Map to setup.
	private NamedMap<String, Double> billingInfo;
	// private Map<String, Double> billingInfo = new HashMap<>() ;

	public Integer getEntriesCount() {
		return billingInfo.size();
	}

	public Map<String, Double> getData() {
		return billingInfo;
	}
}
