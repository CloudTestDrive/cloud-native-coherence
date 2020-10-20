package com.oracle.labs.helidon.coherence.charge.common.data;

import java.io.Serializable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BillingCost", description = "Details of a Billing cost message", example = "{\"user\" : \"Fred\", \"charge\" : 0.5}")
public class BillingCost implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user;
	private Double charge;
}
