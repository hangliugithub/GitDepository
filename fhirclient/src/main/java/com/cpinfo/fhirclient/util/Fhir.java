package com.cpinfo.fhirclient.util;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.IRestfulClientFactory;
import ca.uhn.fhir.rest.client.ServerValidationModeEnum;

public class Fhir {
	private static FhirContext ctx = FhirContext.forDstu2();
	private static IRestfulClientFactory clientFactory  = ctx.getRestfulClientFactory();
	
	
	static{
		// Set how long to try and establish the initial TCP connection (in ms)
		clientFactory.setConnectTimeout(20 * 1000);
		// Set how long to block for individual read/write operations (in ms)
		clientFactory.setSocketTimeout(20 * 1000);
		// Disable server validation (don't pull the server's metadata first)
		clientFactory.setServerValidationMode(ServerValidationModeEnum.NEVER);
	}
	
	private Fhir(){}
	
	public static IRestfulClientFactory getClientFactory(){
		return clientFactory;
	}
	
	public static FhirContext getCtx(){
		return ctx;
	}
}
