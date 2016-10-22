package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.ExtensionDt;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;
import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.ContactPointDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AddressUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.ContactPointSystemEnum;
import ca.uhn.fhir.model.dstu2.valueset.ContactPointUseEnum;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.StringDt;
import oracle.sql.TIMESTAMP;

public class testPatient {
	private static FhirContext ctx = FhirContext.forDstu2();
	Patient p = new Patient();
	
	
	
	//@Before
	public void testSetPatient(){
		p.setId(new IdDt().setValue("1001"));
		p.setActive(true);
		
		ResourceMetadataMap map = new ResourceMetadataMap();
		map.put(ResourceMetadataKeyEnum.VERSION, new TIMESTAMP().toString());
		p.setResourceMetadata(map);
		
		List<HumanNameDt> list = new ArrayList<HumanNameDt>();
		HumanNameDt namedt = new HumanNameDt();
		namedt.setText("刘康");
		list.add(namedt);
		p.setName(list);
		
		List<IdentifierDt> idList= new ArrayList<IdentifierDt>();
		
		idList.add(new IdentifierDt("2.16.840.1.113883.2.23.1.9.1", "110101200301120019"));
		idList.add(new IdentifierDt("2.16.840.1.113883.2.23.1.9.2", "100000000000"));
		p.setIdentifier(idList);
		
		List<ContactPointDt> teList = new ArrayList<ContactPointDt>();
		ContactPointDt cd = new ContactPointDt().setUse(ContactPointUseEnum.MOBILE).setSystem(ContactPointSystemEnum.PHONE).setValue("13800138000");
		teList.add(cd);
		p.setTelecom(teList);
		
		p.setGender(AdministrativeGenderEnum.MALE);
		
		DateDt ddt = new DateDt("2003-01-12");
		ddt.addUndeclaredExtension(new ExtensionDt().setUrl("http://hl7.org/fhir/StructureDefinition/patient-birthTime").setValue(new DateTimeDt("2003-01-12T09:12:35+08:00")));
		p.setBirthDate(ddt);
		
		List<AddressDt> addrDt = new ArrayList<AddressDt>();
		List<StringDt> strDt = new ArrayList<StringDt>();
		strDt.add(new StringDt("景山前街4号"));
		AddressDt adt = new AddressDt().setUse(AddressUseEnum.HOME).setState("北京").setDistrict("东城区").setCity("北京市").setLine(strDt).setPostalCode("100010");
		addrDt.add(adt);
		p.setAddress(addrDt);
		
		List<Patient.Contact> contactList = new ArrayList<Patient.Contact>();
		Patient.Contact pct = new Patient.Contact();
		pct.setName(new HumanNameDt().setText("刘勇")).setGender(AdministrativeGenderEnum.MALE);
		List<ContactPointDt> ctaList = new ArrayList<ContactPointDt>();
		ctaList.add(new ContactPointDt().setSystem(ContactPointSystemEnum.PHONE).setValue("13012345678"));
		pct.setTelecom(ctaList);
		List<CodeableConceptDt> relList = new ArrayList<CodeableConceptDt>();
		List<CodingDt> codingdt = new ArrayList<CodingDt>();
		codingdt.add(new CodingDt().setCode("parent").setSystem("http://hl7.org/fhir/patient-contact-relationship"));
		relList.add(new CodeableConceptDt().setCoding(codingdt));
		pct.setRelationship(relList);
		contactList.add(pct);
		p.setContact(contactList);
		
		String patientXML = ctx.newJsonParser().encodeResourceToString(p);
		
		
		System.out.println(patientXML);
		System.out.println(p.getName().get(0).getText());
	}
	
	@Test
	public void testGetPatient(){
		String id = p.getId().getValueAsString();
		String version = p.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString();
		String idcard = p.getIdentifier().get(0).getSystem()+"-"+p.getIdentifier().get(0).getValue();
		String ssid = p.getIdentifier().get(1).getSystem()+"-"+p.getIdentifier().get(1).getValue();
		String name = p.getName().get(0).getText();
		String gender = p.getGender();
		String birthdate = p.getBirthDate().toString();
		String phone = p.getTelecom().get(0).getValue();
		
		AddressDt adt = p.getAddress().get(0);
		String address = adt.getUse()+"-"+adt.getState()+"-"+adt.getDistrict()+"-"+adt.getCity()+"-"+adt.getLine().get(0).getValue()+"-"+adt.getPostalCode();
		
		Patient.Contact cat = p.getContact().get(0);
		String contact = cat.getRelationship().get(0).getCoding().get(0).getCode()+"-"+cat.getName().getText()+"-"+cat.getGender()+"-"+cat.getTelecomFirstRep().getValue();
		
		String active = p.getActive().toString();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("version",version);
		map.put("idcard", idcard);
		map.put("ssid", ssid);
		map.put("name", name);
		map.put("gender", gender);
		map.put("birthdate", birthdate);
		map.put("phone", phone);
		map.put("address", address);
		map.put("contact", contact);
		map.put("active",active);
		
		 map.entrySet();
		 for(Entry<String,String> ent:map.entrySet()){
			 System.out.println(ent.getKey()+":"+ent.getValue());
		 }
		
	}
	
	@Test
	public void test(){
		String str = "";
	}
	
}
