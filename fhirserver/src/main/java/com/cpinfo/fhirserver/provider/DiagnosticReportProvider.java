package com.cpinfo.fhirserver.provider;

import java.util.List;

import com.cpinfo.fhirserver.service.DiagnosticReportService;
import com.cpinfo.fhirserver.util.AppCtx;

import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.OperationOutcome;
import ca.uhn.fhir.model.dstu2.valueset.IssueSeverityEnum;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.Delete;
import ca.uhn.fhir.rest.annotation.History;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.exceptions.InvalidResponseException;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;

public class DiagnosticReportProvider implements IResourceProvider{
	private DiagnosticReportService service = (DiagnosticReportService) AppCtx.ctx.getBean("diagService");
	
	public Class<DiagnosticReport> getResourceType() {
		return DiagnosticReport.class;
	}

	@Create()
	public MethodOutcome createDiagnosticReport(@ResourceParam DiagnosticReport diag) {
		//validateResource(thePatient);
		//System.out.println(thePatient.getNameFirstRep().getFamilyAsSingleString());
		try {
			DiagnosticReport d = service.createDiag(diag);
			return new MethodOutcome(d.getId(),true);
		} catch (Exception e) {
			e.printStackTrace();
			OperationOutcome opcome = new OperationOutcome();
			opcome.addIssue().setSeverity(IssueSeverityEnum.FATAL).setDetails(new CodeableConceptDt().setText("无效的资源"));
			throw new UnprocessableEntityException(e.getMessage(), opcome);
		}
	}
	
	
	@Read(version = false)
	public DiagnosticReport readDiagnosticReport(@IdParam IdDt id) {
		try {
			System.out.println(id.getValue().split("/")[1]);
			DiagnosticReport d= service.readDiag(id.getValue().split("/")[1]);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Update()
	public MethodOutcome updateDiagnosticReport(@IdParam IdDt id, @ResourceParam DiagnosticReport diag) {

		try {
			diag.setId(id.getValue().split("/")[1]);
			diag = service.updateDiagnosticReport(diag);
		} catch (Exception e) {
			//throw new InvalidRequestException("Invalid ID " + theId.getValue() + " - Must be numeric");
			e.printStackTrace();
			throw new InvalidResponseException(400, e.getMessage());
		}
		MethodOutcome out = new MethodOutcome();
		out.setResource(diag);
		return out;
	}
	
	
	@Search()
	public List<DiagnosticReport> findDiagnosticReportsByName(@RequiredParam(name = DiagnosticReport.SP_CODE) StringDt name) {
		try {
			System.out.println(name);
			
			List<DiagnosticReport> list = service.findByName(name.getValue());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidResponseException(400, e.getMessage());
		}
	}
	
	@Delete
	public void deleteDiagnosticReport(@IdParam IdDt id) {
		try {
			int num = service.delDiag(id.getValue().split("/")[1]);
			if (num<=0)
		        throw new ResourceNotFoundException("未找到");
		} catch (Exception e) {
			throw new InvalidResponseException(400, e.getMessage());
		}
	}
	
	@History()
	public List<DiagnosticReport> getDiagnosticReportHistory(@IdParam IdDt id) {
		try {
			System.out.println(id);
			List<DiagnosticReport> list = service.findHisDiag(id.getValue().split("/")[1]);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidResponseException(400, e.getMessage());
		}
	}
}
