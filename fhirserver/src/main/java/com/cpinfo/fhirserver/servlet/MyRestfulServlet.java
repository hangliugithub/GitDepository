package com.cpinfo.fhirserver.servlet;

import java.util.ArrayList;
import java.util.List;

import com.cpinfo.fhirserver.provider.ConditionProvider;
import com.cpinfo.fhirserver.provider.DiagnosticReportProvider;
import com.cpinfo.fhirserver.provider.ObservationProvider;
import com.cpinfo.fhirserver.provider.PatientProvider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.INarrativeGenerator;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;

public class MyRestfulServlet extends RestfulServer {
	private static final long serialVersionUID = 2973870641316437130L;

	public MyRestfulServlet() {
		super(FhirContext.forDstu2()); // Support DSTU2
	}

	@Override
	public void initialize() {
		/*
		 * 添加处理某种资源的Provider
		 */
		List<IResourceProvider> providers = new ArrayList<IResourceProvider>();
		providers.add(new PatientProvider());
		providers.add(new ConditionProvider());
		providers.add(new ObservationProvider());
		providers.add(new DiagnosticReportProvider());
		setResourceProviders(providers);

		/*
		 * Use a narrative generator. This is a completely optional step, but
		 * can be useful as it causes HAPI to generate narratives for resources
		 * which don't otherwise have one.
		 */
		// INarrativeGenerator narrativeGen = new
		// DefaultThymeleafNarrativeGenerator();
		// getFhirContext().setNarrativeGenerator(narrativeGen);

		/*
		 * This server interceptor causes the server to return nicely formatter
		 * and coloured responses instead of plain JSON/XML if the request is
		 * coming from a browser window. It is optional, but can be nice for
		 * testing.
		 */
		// registerInterceptor(new ResponseHighlighterInterceptor());

		/*
		 *格式化输出json或者xml字符串
		 */
		setDefaultPrettyPrint(true);

	}

}
