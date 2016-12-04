package com.cpinfo.fhirserver.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cpinfo.fhirserver.dao.PatientDao;

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
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.model.primitive.StringDt;

@Component
public class PatientService{
	
	@Resource(name="patientDao")
	private PatientDao patientDao;
	
	/**
	 * 创建病人
	 * @param str
	 * @param strType
	 * @return
	 */
	public Patient createPatient(Patient p){
		
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		//metaMap.put(ResourceMetadataKeyEnum.UPDATED, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		p.setResourceMetadata(metaMap);
		
		Map<String,String> patientMap = getPatientInfo(p);
		patientDao.creat(patientMap);
		p.setId(patientMap.get("id"));
		return p;
	}
	
	/**
	 * 根据id查找一个Painten资源
	 * @param id
	 * @param strType
	 * @return
	 * @throws ParseException 
	 */
	public Patient readPatient(String id) throws ParseException{
		Map<String,String> map = patientDao.findById(id);
		if(map==null||map.isEmpty()){
			throw new RuntimeException("未找到");
		}
		Patient p = setPatientInfo(map);
		return p;
	}
	
	
	/**
	 * 根据id查找对应Patient资源的所有历史版本
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	public List<Patient> findHisPatient(String id) throws ParseException{
		List<Map<String,String>> patientList = patientDao.findHisById(id);
		if(patientList==null||patientList.isEmpty()){
			throw new RuntimeException("未找到");
		}
		List<Patient> patients = new ArrayList<Patient>();
		for(Map<String,String> map:patientList){
			Patient p= setPatientInfo(map);
			patients.add(p);
		}
		return patients;
	}
	
	/**
	 * 根据name查找Patient资源
	 * @param name
	 * @param strType
	 * @return
	 * @throws ParseException 
	 */
	public List<Patient> findPatientByName(String name) throws ParseException{
		List<Map<String,String>> patientMapList = patientDao.findByName(name);
		if(patientMapList==null||patientMapList.isEmpty()){
			throw new RuntimeException("未找到");
		}
		List<Patient> patients = new ArrayList<Patient>();
		for(Map<String,String> m:patientMapList){
			Patient p = setPatientInfo(m);
			patients.add(p);	
		}
		return patients;
	}
	
	
	
	/**
	 * 更新病人信息
	 * @param str
	 * @param strType
	 * @return
	 */
	public Patient updatePatient(Patient p){
//		Patient p = null;
//		p = MyParser.parseToObject(str, strType, Patient.class);
		
		//p.setId(id);
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		p.setResourceMetadata(metaMap);
		Map<String,String> patientMap = getPatientInfo(p);
		patientDao.setActive(p.getId().getValueAsString());
		patientDao.update(patientMap);
		return p;
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public int delPatient(String id){
		
		return patientDao.delById(id);
	}
	
	/**
	 * 将一个Patient资源对象中的信息映射到map
	 * @param p 
	 * @return
	 */
	private Map<String,String> getPatientInfo(Patient p){
		String id = p.getId().getValueAsString();
		String version = p.getResourceMetadata()
				.get(ResourceMetadataKeyEnum.VERSION).toString();
		String idcard = p.getIdentifierFirstRep().getValue();
		String ssid = p.getIdentifier().get(1).getValue();
		String name = p.getNameFirstRep().getText();
		if(!p.getNameFirstRep().getFamilyFirstRep().isEmpty()){
			name=p.getNameFirstRep().getFamilyFirstRep().getValue()
					+""+p.getNameFirstRep().getGivenFirstRep().getValue();
		}
		String gender = p.getGender();
		
		String birthdate = "";
		if(p.getBirthDate()==null){
			birthdate = "no";
		}
		birthdate = p.getBirthDate().getTime()+"";
		String phone = p.getTelecom().get(0).getValue();
		
		AddressDt adt = p.getAddress().get(0);
		String address = adt.getState()+"-"+adt.getDistrict()+"-"+adt.getCity()+"-"
						+adt.getLine().get(0).getValue()+"-"+adt.getPostalCode();
		
		Patient.Contact cat = p.getContact().get(0);
		String contact = cat.getRelationship().get(0).getCoding().get(0).getCode()
				+"-"+cat.getName().getText()+"-"+cat.getGender()+"-"+cat.getTelecomFirstRep().getValue();
		
		//String active = p.getActive().toString();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("uuid", UUID.randomUUID().toString());
		map.put("id", id);
		map.put("version",version);
		map.put("idcard", idcard);
		map.put("sscard", ssid);
		map.put("name", name);
		map.put("gender", gender);
		map.put("birthdate", birthdate);
		map.put("phone", phone);
		map.put("address", address);
		map.put("contact", contact);
		
		for(Entry<String,String> ent:map.entrySet()){
			System.out.println(ent.getKey()+":"+ent.getValue());
		}
		
		return map;
	}
	
	/**
	 * 将一个map中的病人信息还原成Patient对象
	 * @param patientMap
	 * @return
	 * @throws ParseException 
	 */
	private Patient setPatientInfo(Map<String,String> patientMap) throws ParseException{
		Patient p = new Patient();
		
		for(Entry<String,String> ent:patientMap.entrySet()){
			System.out.println(ent.getKey()+":"+ent.getValue());
		}
		
		String id = patientMap.get("ID")+"";
		String version = patientMap.get("VERSION")+"";
		String idcard = patientMap.get("IDCARD")+"";
		String ssid = patientMap.get("SSCARD")+"";
		String name = patientMap.get("NAME")+"";
		String gender =patientMap.get("GENDER")+"";
		String birthdate = patientMap.get("BIRTHDATE")+"";
		String phone = patientMap.get("PHONE")+"";
		String address = patientMap.get("ADDRESS")+"";
		String contact = patientMap.get("CONTACT")+"";
		String active =  patientMap.get("ACTIVE")+"";
		
		p.setId(new IdDt().setValue(id).withVersion(version));
		if("0".equalsIgnoreCase(active))
		p.setActive(true);
		else
		p.setActive(false);
		
		ResourceMetadataMap map = new ResourceMetadataMap();
		//map.put(ResourceMetadataKeyEnum.VERSION, version);
		map.put(ResourceMetadataKeyEnum.UPDATED,
				new InstantDt(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").parse(version)));
		p.setResourceMetadata(map);
		
		List<HumanNameDt> list = new ArrayList<HumanNameDt>();
		HumanNameDt namedt = new HumanNameDt();
		namedt.setText(name);
		list.add(namedt);
		p.setName(list);
		
		List<IdentifierDt> idList= new ArrayList<IdentifierDt>();
		
		idList.add(new IdentifierDt("2.16.840.1.113883.2.23.1.9.1", idcard));
		idList.add(new IdentifierDt("2.16.840.1.113883.2.23.1.9.2", ssid));
		p.setIdentifier(idList);
		
		List<ContactPointDt> teList = new ArrayList<ContactPointDt>();
		ContactPointDt cd = new ContactPointDt().setUse(ContactPointUseEnum.MOBILE)
				.setSystem(ContactPointSystemEnum.PHONE).setValue(phone);
		teList.add(cd);
		p.setTelecom(teList);
		
		if("male".equalsIgnoreCase(gender))
			p.setGender(AdministrativeGenderEnum.MALE);
		else
			p.setGender(AdministrativeGenderEnum.FEMALE);
		
		if(!"no".equalsIgnoreCase(birthdate)){
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			DateDt ddt = new DateDt(new Date(Long.valueOf(birthdate)));
			ddt.addUndeclaredExtension(new ExtensionDt()
					.setUrl("http://hl7.org/fhir/StructureDefinition/patient-birthTime")
					.setValue(new DateTimeDt(new Date(Long.valueOf(birthdate)))));
			p.setBirthDate(ddt);
		}else{
			p.setBirthDate(new DateDt(new Date()));
		}
		
		List<AddressDt> addrDt = new ArrayList<AddressDt>();
		List<StringDt> strDt = new ArrayList<StringDt>();
		String[] addrStr = address.split("-");
 		strDt.add(new StringDt(addrStr[3]));
		AddressDt adt = new AddressDt().setUse(AddressUseEnum.HOME)
				.setState(addrStr[0]).setDistrict(addrStr[1])
				.setCity(addrStr[2]).setLine(strDt).setPostalCode(addrStr[4]);
		addrDt.add(adt);
		p.setAddress(addrDt);
		
		
		String[] contactStr = contact.split("-");
		List<Patient.Contact> contactList = new ArrayList<Patient.Contact>();
		Patient.Contact pct = new Patient.Contact();
		pct.setName(new HumanNameDt().setText(contactStr[1]));
		
		if("male".equalsIgnoreCase(contactStr[2]))
			pct.setGender(AdministrativeGenderEnum.MALE);
		else
			pct.setGender(AdministrativeGenderEnum.FEMALE);
		
		List<ContactPointDt> ctaList = new ArrayList<ContactPointDt>();
		ctaList.add(new ContactPointDt()
				.setSystem(ContactPointSystemEnum.PHONE).setValue(contactStr[3]));
		pct.setTelecom(ctaList);
		List<CodeableConceptDt> relList = new ArrayList<CodeableConceptDt>();
		List<CodingDt> codingdt = new ArrayList<CodingDt>();
		codingdt.add(new CodingDt().setCode(contactStr[0])
				.setSystem("http://hl7.org/fhir/patient-contact-relationship"));
		relList.add(new CodeableConceptDt().setCoding(codingdt));
		pct.setRelationship(relList);
		contactList.add(pct);
		p.setContact(contactList);
		
		//System.out.println(MyParser.parseToJSON(p));
		
		return p;
	}
	
}
