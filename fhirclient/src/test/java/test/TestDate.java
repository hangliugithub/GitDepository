package test;

import java.util.Date;

import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.InstantDt;

public class TestDate {
	public static void main(String[] args) {
		System.out.println(new Date().toInstant().toString());
		System.out.println(new Date().toString());
		System.out.println(new DateTimeDt(new Date()).getValueAsString());
		System.out.println(new DateTimeDt(new Date()).getTimeZone());
		System.out.println(new DateTimeDt("2016-10-31T12:51:26").getValueAsString());
		
	}
}
