package com.oracle.labs.helidon.coherence.clients.test.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import com.oracle.labs.helidon.coherence.common.data.BillingCost;
import com.oracle.labs.helidon.coherence.common.resources.CoherenceChargeRESTResource;

public class ChargeTestREST {

	public static void main(String[] args) throws IOException {
		// setup the inputs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(
				"Please enter the username to make the request with (jack, jill, joe, press return for default of jack) :");
		String requestUser = br.readLine();
		if (requestUser.length() == 0) {
			requestUser = "jack";
		}
		System.out.print("Please enter the password to make the request with (press return for default of password) :");
		String requestPassword = br.readLine();
		if (requestPassword.length() == 0) {
			requestPassword = "password";
		}
		System.out.print("Please enter the name to charge against :");
		String userToCharge = br.readLine();
		Double chargeToCharge = null;
		while (chargeToCharge == null) {
			System.out.print("Please enter the amount to charge as a double :");
			String chargeToChargeStr = br.readLine();
			try {
				chargeToCharge = Double.valueOf(chargeToChargeStr);
			} catch (NumberFormatException nfe) {
				System.out.println("Your input " + chargeToChargeStr + " did not parse as a double");
				continue;
			}
		}
		System.out.println("About to try and request a charge of " + chargeToCharge + " against " + userToCharge
				+ " using request user " + requestUser + " and reqqueszt password " + requestPassword);
		// build the REST client
		CoherenceChargeRESTResource coherenceChargeRESTResource = RestClientBuilder.newBuilder()
				.baseUri(URI.create("http://localhost:8082")).register(new AuthBuilder(requestUser, requestPassword))
				.build(CoherenceChargeRESTResource.class);
		BillingCost bc = coherenceChargeRESTResource.updateCharges(new BillingCost(userToCharge, chargeToCharge));
		System.out.println("Got a return of " + bc);
	}

}
