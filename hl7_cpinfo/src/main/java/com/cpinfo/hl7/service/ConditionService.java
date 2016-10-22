package com.cpinfo.hl7.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpinfo.hl7.dao.ConditionDao;

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
import ca.uhn.fhir.model.primitive.DateTimeDt;

@Service("conditionService")
public class ConditionService {
	@Autowired
	private ConditionDao conditionDao;
	
	/**
	 * 创建一个诊断
	 * @param str 传入的数据字符串
	 * @param strType 字符串类型
	 * @return 创建好的诊断
	 */
	public Condition createCondition(Condition condition){
		//System.out.println("str:"+str);
//		if((str==null||str.length()==0) || (strType==null||strType.length()==0)){
//			throw new RuntimeException("字符串或字符串类型不能为空");
//		}
//		Condition condition = MyParser.parseToObject(str, strType, Condition.class);
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		condition.setResourceMetadata(metaMap);
		
		Map<String,String> conditionMap = getConditionInfo(condition);
		conditionDao.creat(conditionMap);
		condition.setId(conditionMap.get("id"));
		
		return condition;
	}
	
	/**
	 * 根据id读取一个Condition
	 * @param id
	 * @return
	 */
	public Condition readCondition(String id){
		Map<String,String> map = conditionDao.findById(id);
		if(map==null||map.isEmpty()){
			throw new RuntimeException("未找到");
		}
		Condition condition = setConditionInfo(map);
		return condition;
	}
	
	/**
	 * 根据id查找该资源的所有历史版本
	 * @param id
	 * @return
	 */
	public List<Condition> findHisCondition(String id){
		List<Map<String,String>> conditionList = conditionDao.findHisById(id);
		if(conditionList==null||conditionList.isEmpty()){
			throw new RuntimeException("未找到");
		}
		List<Condition> conditions = new ArrayList<Condition>();
		for(Map<String,String> map:conditionList){
			Condition c = setConditionInfo(map);
			conditions.add(c);
		}
		return conditions;
	}
	
	/**
	 * 更新一个Condition
	 * @param id
	 * @param str
	 * @param strType
	 * @return
	 */
	public Condition updateCondition(Condition condition){
//		Condition condition = new Condition();
//		condition = MyParser.parseToObject(str, strType, Condition.class);
//		if(condition==null){
//			throw new RuntimeException("字符串解析失败");
//		}
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		condition.setResourceMetadata(metaMap);
		Map<String,String> conditionMap = getConditionInfo(condition);
		//conditionMap.put("id", id);
		conditionDao.setActive(condition.getId().getValueAsString());
		conditionDao.update(conditionMap);
		
		return condition;
	}
	
	/**
	 * 根据id删除一个condition
	 * @param id
	 * @return
	 */
	public int delCondition(String id){
		return conditionDao.delById(id);
	}
	
	/**
	 * 根据关联患者id查找condition
	 * @param patientId
	 * @return
	 */
	public List<Condition> findByPatientId(String patientId){
		List<Map<String,String>> conditionList = conditionDao.findByPatientId(patientId);
		if(conditionList==null||conditionList.isEmpty()){
			throw new RuntimeException("未找到");
		}
		List<Condition> conditions = new ArrayList<Condition>();
		for(Map<String,String> map:conditionList){
			Condition c = setConditionInfo(map);
			conditions.add(c);
		}
		return conditions;
	}
	
	/**
	 * 根据诊断名称查找condition
	 * @param asserterId
	 * @return
	 */
	public List<Condition> findByName(String conditionName){
		List<Map<String,String>> conditionList = conditionDao.findByName(conditionName);
		if(conditionList==null||conditionList.isEmpty()){
			throw new RuntimeException("未找到");
		}
		List<Condition> conditions = new ArrayList<Condition>();
		for(Map<String,String> map:conditionList){
			Condition c = setConditionInfo(map);
			conditions.add(c);
		}
		return conditions;
	}
	
	/**
	 * 将condition资源中的信息映射成map
	 * @param condition
	 * @return
	 */
	private Map<String,String> getConditionInfo(Condition condition){
		try{
			String id = condition.getId().getValue();
			String version = condition.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString();
			String text = condition.getText().getDivElement().getValueAsString();
			String patientid = condition.getPatient().getReference().getValueAsString();
			String encounterid = condition.getEncounter().getReference().getValueAsString();
			String asserterid = condition.getAsserter().getReference().getValueAsString();
			String code = condition.getCode().getCoding().get(0).getCode()+"-"+condition.getCode().getCoding().get(0).getDisplay();
			//String category = condition.getCategory().getCoding().get(0).getCode()+"-"+condition.getCategory().getCoding().get(0).getDisplay();
			String clinicalstatus = condition.getClinicalStatus();
			String verificationstatus = condition.getVerificationStatus();
			String onset = ((DateTimeDt)condition.getOnset()).getValueAsString();
			//new DateTimeDt(new Date()).toHumanDisplay();
			Map<String,String> map = new HashMap<String, String>();
			map.put("uuid", UUID.randomUUID().toString());
			map.put("id",id );
			map.put("version",version+"");
			map.put("text", text+"");
			map.put("patientid",patientid+"");
			map.put("encounterid",encounterid+"" );
			map.put("asserterid", asserterid+"");
			map.put("code", code+"");
			//map.put("category",category );
			map.put("clinicalstatus", clinicalstatus+"");
			map.put("verificationstatus", verificationstatus+"");
			map.put("onset",onset+"");
		
			for(Entry<String,String> ent:map.entrySet()){
				System.out.println(ent.getKey()+":"+ent.getValue());
			}
			
			return map;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("condition信息不完整",e);
		}
	}

	/**
	 * 将map中的数据还原回一个condition对象中
	 * @param map
	 * @return
	 */
	private Condition setConditionInfo(Map<String,String> map){
		
		String id = map.get("ID")+"";
		String version = map.get("VERSION")+"";
		String text = map.get("TEXT")+"";
		String patientid = map.get("PATIENTID")+"";
		String encounterid = map.get("ENCOUNTERID")+"";
		String asserterid = map.get("ASSERTERID")+"";
		String code = map.get("CODE")+"";
		//String category = map.get("CATEGORY")+"";
		String clinicalstatus = map.get("CLINICALSTATUS")+"";
		String verificationstatus = map.get("VERIFICATIONSTATUS")+"";
		String onset = map.get("ONSET")+"";
		
		
		Condition condition = new Condition();
		
		condition.setId(id);
		
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, version);
		condition.setResourceMetadata(metaMap);
		
		
		NarrativeDt  theText = new NarrativeDt();
		theText.setStatus(NarrativeStatusEnum.GENERATED);
		theText.setDiv(text);
		condition.setText(theText);
		
		condition.setPatient(new ResourceReferenceDt(patientid));
		
		condition.setEncounter(new ResourceReferenceDt(encounterid));
		
		condition.setAsserter(new ResourceReferenceDt(asserterid));
		
		CodeableConceptDt theCode = new CodeableConceptDt();
		List<CodingDt> theCodingDt =  new ArrayList<CodingDt>();
		String[] codes = code.split("-");
		theCodingDt.add(new CodingDt().setSystem("http://www.icd10data.com/icd10pcs").setCode(codes[0]).setDisplay(codes[1]));
		theCode.setCoding(theCodingDt);
		condition.setCode(theCode);
		
		//condition.setCategory(ConditionCategoryCodesEnum.DIAGNOSIS);
		@SuppressWarnings("deprecation")
		BoundCodeableConceptDt<ConditionCategoryCodesEnum> theCodeDt = new BoundCodeableConceptDt<ConditionCategoryCodesEnum>();
		theCodeDt.addCoding(new CodingDt().setSystem("http://hl7.org/fhir/condition-category").setCode("diagnosis").setDisplay("诊断"));
		condition.setCategory(theCodeDt);
		
		if("active".equalsIgnoreCase(clinicalstatus))
			condition.setClinicalStatus(ConditionClinicalStatusCodesEnum.ACTIVE);
		
		if("relapse".equalsIgnoreCase(clinicalstatus))
			condition.setClinicalStatus(ConditionClinicalStatusCodesEnum.RELAPSE);
		
		if("remission".equalsIgnoreCase(clinicalstatus))
			condition.setClinicalStatus(ConditionClinicalStatusCodesEnum.REMISSION);
		
		if("resolved".equalsIgnoreCase(clinicalstatus))
			condition.setClinicalStatus(ConditionClinicalStatusCodesEnum.RESOLVED);
		
		if("confirmed".equalsIgnoreCase(verificationstatus))
			condition.setVerificationStatus(ConditionVerificationStatusEnum.CONFIRMED);
		else
			condition.setVerificationStatus(ConditionVerificationStatusEnum.PROVISIONAL);
		
		condition.setOnset(new DateTimeDt(onset));
		
		return condition;
	}
}
