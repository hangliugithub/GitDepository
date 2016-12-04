package com.cpinfo.fhirserver.provider;

import java.util.List;

import com.cpinfo.fhirserver.service.ConditionService;
import com.cpinfo.fhirserver.util.AppCtx;

import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.resource.Condition;
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

public class ConditionProvider implements IResourceProvider {

	private ConditionService service = (ConditionService) AppCtx.ctx.getBean("conditionService");
	
	public Class<Condition> getResourceType() {
		return Condition.class;
	}
	
	@Create()
	public MethodOutcome createCondition(@ResourceParam Condition condition) {
		//validateResource(thePatient);
		//System.out.println(thePatient.getNameFirstRep().getFamilyAsSingleString());
		try {
			Condition c = service.createCondition(condition);
			return new MethodOutcome(c.getId(),true);
		} catch (Exception e) {
			e.printStackTrace();
			OperationOutcome opcome = new OperationOutcome();
			opcome.addIssue().setSeverity(IssueSeverityEnum.FATAL).setDetails(new CodeableConceptDt().setText("无效的资源"));
			throw new UnprocessableEntityException(e.getMessage(), opcome);
		}
	}
	
	
	@Read(version = false)
	public Condition readCondition(@IdParam IdDt id) {
		try {
			System.out.println("readCondition.id="+id);
			Condition p = service.readCondition(id.getValue().split("/")[1]);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			//return null;
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Update()
	public MethodOutcome updateCondition(@IdParam IdDt id, @ResourceParam Condition condition) {

		try {
			System.out.println("updateCondition.id="+id);
			condition.setId(id.getValue().split("/")[1]);
			condition = service.updateCondition(condition);
		} catch (Exception e) {
			//throw new InvalidRequestException("Invalid ID " + theId.getValue() + " - Must be numeric");
			e.printStackTrace();
			throw new InvalidResponseException(400, e.getMessage());
		}
		return new MethodOutcome(condition.getIdElement());
	}
	
	
	@Search()
	public List<Condition> findConditionsByName(@RequiredParam(name = Condition.SP_CODE) StringDt name) {
		try {
			System.out.println("findConditionsByName.name="+name);
			
			List<Condition> list = service.findByName(name.getValue());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidResponseException(400, e.getMessage());
		}
	}
	
	@Delete
	public void deleteCondition(@IdParam IdDt id) {
		System.out.println("deleteCondition.id="+id);
		try {
			int num = service.delCondition(id.getValue().split("/")[1]);
			if (num<=0)
		        throw new ResourceNotFoundException("未找到");
		} catch (Exception e) {
			throw new InvalidResponseException(400, e.getMessage());
		}
	}
	
	@History()
	public List<Condition> getConditionHistory(@IdParam IdDt id) {
		System.out.println("getConditionHistory.id="+id);
		try {
			List<Condition> list = service.findHisCondition(id.getValue().split("/")[1]);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidResponseException(400, e.getMessage());
		}
	}
	
	
	

}
