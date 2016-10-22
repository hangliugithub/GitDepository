package test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.PeriodDt;
import ca.uhn.fhir.model.dstu2.composite.SimpleQuantityDt;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.DecimalDt;

public class Test {
	public static void main(String[] args) {
		System.out.println(new DateDt(Calendar.getInstance()).getValueAsString());
		//System.out.println(new DateTimeDt(new Date(),TemporalPrecisionEnum.MINUTE).getValueAsString());
		Observation o = new Observation();
		o.setCode(new CodeableConceptDt("http://www.bmc.nl/zorgportal/identifiers/observations", "718-7"));
		System.out.println(Observation.SP_CODE_VALUE_QUANTITY);
		o.setValue(new SimpleQuantityDt(new Double(2.5), "http://unitsofmeasure.org", "g/dl"));
		System.out.println(o.getValue());
		o.setEffective(new PeriodDt().setStart(new DateTimeDt(new Date())).setEnd(new DateTimeDt(new Date())));
		System.out.println(((PeriodDt)o.getEffective()).getStart());
		System.out.println(new BigDecimal("2.5"));
		
	}
}	
