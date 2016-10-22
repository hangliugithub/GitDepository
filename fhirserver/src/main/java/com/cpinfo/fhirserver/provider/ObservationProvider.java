package com.cpinfo.fhirserver.provider;

import java.util.List;

import com.cpinfo.fhirserver.service.ObservationService;
import com.cpinfo.fhirserver.util.AppCtx;

import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.resource.Observation;
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

public class ObservationProvider implements IResourceProvider {

	private ObservationService service = (ObservationService) AppCtx.ctx.getBean("observationService");
	
	public Class<Observation> getResourceType() {
		return Observation.class;
	}
	
	@Create()
	public MethodOutcome createObservation(@ResourceParam Observation obs) {
		//validateResource(thePatient);
		//System.out.println(thePatient.getNameFirstRep().getFamilyAsSingleString());
		try {
			Observation o = service.createObservation(obs);
			return new MethodOutcome(o.getId(),true);
		} catch (Exception e) {
			e.printStackTrace();
			OperationOutcome opcome = new OperationOutcome();
			opcome.addIssue().setSeverity(IssueSeverityEnum.FATAL).setDetails(new CodeableConceptDt().setText("无效的资源"));
			throw new UnprocessableEntityException(e.getMessage(), opcome);
		}
	}
	
	
	@Read(version = false)
	public Observation readObservation(@IdParam IdDt id) {
		try {
			System.out.println(id.getValue().split("/")[1]);
			Observation o= service.readObservation(id.getValue().split("/")[1]);
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Update()
	public MethodOutcome updateObservation(@IdParam IdDt id, @ResourceParam Observation obs) {

		try {
			obs.setId(id.getValue().split("/")[1]);
			obs = service.updateObservation(obs);
		} catch (Exception e) {
			//throw new InvalidRequestException("Invalid ID " + theId.getValue() + " - Must be numeric");
			e.printStackTrace();
			throw new InvalidResponseException(400, e.getMessage());
		}
		MethodOutcome out = new MethodOutcome();
		out.setResource(obs);
		return out;
	}
	
	
	@Search()
	public List<Observation> findObservationsByName(@RequiredParam(name = Observation.SP_CODE) StringDt name) {
		try {
			System.out.println(name);
			
			List<Observation> list = service.findByName(name.getValue());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidResponseException(400, e.getMessage());
		}
	}
	
	@Delete
	public void deleteObservation(@IdParam IdDt id) {
		try {
			int num = service.delObservation(id.getValue().split("/")[1]);
			if (num<=0)
		        throw new ResourceNotFoundException("未找到");
		} catch (Exception e) {
			throw new InvalidResponseException(400, e.getMessage());
		}
	}
	
	@History()
	public List<Observation> getObservationHistory(@IdParam IdDt id) {
		try {
			System.out.println(id);
			List<Observation> list = service.findHisObservation(id.getValue().split("/")[1]);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidResponseException(400, e.getMessage());
		}
	}
	
	
	

}
