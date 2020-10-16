package com.oracle.labs.helidon.coherence.common.data;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BillingCost", description = "Details of a Billing cost message", example = "{\"user\" : \"Fred\", \"charge\" : 0.5}")
public class BillingCost {
	private String user;
	private Double charge;
}
