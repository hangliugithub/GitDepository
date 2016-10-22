package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;
import ca.uhn.fhir.model.dstu2.composite.BoundCodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.NarrativeDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Condition;
import ca.uhn.fhir.model.dstu2.valueset.ConditionCategoryCodesEnum;
import ca.uhn.fhir.model.dstu2.valueset.ConditionClinicalStatusCodesEnum;
import ca.uhn.fhir.model.dstu2.valueset.ConditionVerificationStatusEnum;
import ca.uhn.fhir.model.dstu2.valueset.NarrativeStatusEnum;
import ca.uhn.fhir.model.primitive.DateDt;

public class TestCondition {
	private static FhirContext ctx = FhirContext.forDstu2();
	Condition condition = new Condition();
	
	@Before
	public void setConditionInfo(){
		
		condition.setId("123456");
		
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		condition.setResourceMetadata(metaMap);
		
		
		NarrativeDt  theText = new NarrativeDt();
		theText.setStatus(NarrativeStatusEnum.GENERATED);
		theText.setDiv("É³ÃÅ¾úÎ¸³¦Ñ×, 2016-07-14");
		condition.setText(theText);
		
		condition.setPatient(new ResourceReferenceDt("657"));
		
		condition.setEncounter(new ResourceReferenceDt("221"));
		
		condition.setAsserter(new ResourceReferenceDt("890"));
		
		CodeableConceptDt theCode = new CodeableConceptDt();
		List<CodingDt> theCodingDt =  new ArrayList<CodingDt>();
		theCodingDt.add(new CodingDt().setSystem("http://www.icd10data.com/icd10pcs").setCode("A02.003").setDisplay("É³ÃÅ¾úÎ¸³¦Ñ×"));
		theCode.setCoding(theCodingDt);
		condition.setCode(theCode);
		
		//condition.setCategory(ConditionCategoryCodesEnum.DIAGNOSIS);
		BoundCodeableConceptDt<ConditionCategoryCodesEnum> theCodeDt = new BoundCodeableConceptDt<ConditionCategoryCodesEnum>();
		theCodeDt.addCoding(new CodingDt().setSystem("http://hl7.org/fhir/condition-category").setCode("diagnosis").setDisplay("Õï¶Ï"));
		condition.setCategory(theCodeDt);
		
		condition.setClinicalStatus(ConditionClinicalStatusCodesEnum.ACTIVE);
		
		condition.setVerificationStatus(ConditionVerificationStatusEnum.CONFIRMED);
		
		condition.setOnset(new DateDt(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
		
	}
}
