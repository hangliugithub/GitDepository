package test;

import org.junit.Test;

import com.cpinfo.fhirclient.util.MyParser;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;

public class testClient {
	
	public static void main(String[] args) {
		FhirContext ctx = FhirContext.forDstu2();
		String serverBase = "http://spark.furore.com/fhir";
		//String serverBase = "http://127.0.0.1:8080/hl7_cpinfo";
		IGenericClient client = ctx.newRestfulGenericClient(serverBase);
		//read
		Patient patient = client.read()
	            .resource(Patient.class)
	            .withId("1016")
	            .execute();
		System.out.println(MyParser.parseToJSON(patient));
		
		patient.setGender(AdministrativeGenderEnum.FEMALE);
	}
	
	//@Test
	public void test(){
		FhirContext ctx = FhirContext.forDstu2();
		String serverBase = "http://spark.furore.com/fhir";
		//String serverBase = "http://127.0.0.1:8080/hl7_cpinfo";
		IGenericClient client = ctx.newRestfulGenericClient(serverBase);
		
		//read
		Patient patient = client.read()
	            .resource(Patient.class)
	            .withId("spark7524")
	            .execute();
		System.out.println(MyParser.parseToJSON(patient));
		
		patient.setGender(AdministrativeGenderEnum.FEMALE);
		
		//update
//		MethodOutcome outcome = client.update().resource(patient).encodedJson().execute();
//		IdDt id = (IdDt) outcome.getId();
//		System.out.println("Got ID: " + id.getValue());
		
		
		//create
		//MethodOutcome outcom = client.create().resource(patient).encodedJson().execute();
		
	}
	
	
	
}
