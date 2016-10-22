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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpinfo.fhirserver.dao.ObservationDao;

import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.NarrativeDt;
import ca.uhn.fhir.model.dstu2.composite.PeriodDt;
import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.composite.SimpleQuantityDt;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Observation.ReferenceRange;
import ca.uhn.fhir.model.dstu2.valueset.IdentifierUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.NarrativeStatusEnum;
import ca.uhn.fhir.model.dstu2.valueset.ObservationStatusEnum;
import ca.uhn.fhir.model.dstu2.valueset.QuantityComparatorEnum;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;

@Service("observationService")
public class ObservationService {
	@Autowired
	private ObservationDao obsDao;
	
	/**
	 * 新增检验项目结果observation
	 * @param str
	 * @param strType
	 * @return
	 */
	public Observation createObservation(Observation obs){
//		if((str==null||str.length()==0) || (strType==null||strType.length()==0)){
//			throw new RuntimeException("字符串或字符串类型不能为空");
//		}
//		Observation obs = MyParser.parseToObject(str, strType, Observation.class);
		//System.out.println(p);
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		obs.setResourceMetadata(metaMap);
		
		Map<String,String> obsMap = getObseInfo(obs);
		obsDao.creat(obsMap);
		obs.setId(obsMap.get("id"));
		return obs;
	}
	
	/**
	 * 根据id查找一个Observation资源
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	public Observation readObservation(String id) throws ParseException{
		Map<String,String> map = obsDao.findById(id);
		if(map==null||map.isEmpty()){
			throw new RuntimeException("未找到");
		}
		 
		Observation obs = setObseInfo(map);
		return obs;
	}
	
	/**
	 * 根据id更新一个Observation
	 * @param id
	 * @param str
	 * @param strType
	 * @return
	 */
	public Observation updateObservation(Observation obs){
//		Observation obs = null;
//		obs = MyParser.parseToObject(str, strType, Observation.class);
//		obs.setId(id);
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		obs.setResourceMetadata(metaMap);
		Map<String,String> obsMap = getObseInfo(obs);
		obsDao.setActive(obs.getId().getValueAsString());
		obsDao.update(obsMap);
		return obs;
	}
	
	/**
	 * 根据id删除一个Observation
	 * @param id
	 * @return
	 */
	public int delObservation(String id){
		return obsDao.delById(id);
	}
	
	/**
	 * 根据id查找该observation的所有历史版本
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	public List<Observation> findHisObservation(String id) throws ParseException{
		List<Map<String,String>> obsList = obsDao.findHisById(id);
		if(obsList==null||obsList.isEmpty()){
			throw new RuntimeException("未找到");
		}
		List<Observation> obs = new ArrayList<Observation>();
		for(Map<String,String> map:obsList){
			Observation p= setObseInfo(map);
			obs.add(p);
		}
		return obs;
	}
	
	/**
	 * 通过检验项目名称查询observation
	 * @param name
	 * @return
	 * @throws ParseException
	 */
	public List<Observation> findByName(String name) throws ParseException{
		List<Map<String,String>> obsList = obsDao.findByName(name);
		if(obsList==null||obsList.isEmpty()){
			throw new RuntimeException("未找到");
		}
		List<Observation> obs = new ArrayList<Observation>();
		for(Map<String,String> map:obsList){
			Observation p= setObseInfo(map);
			obs.add(p);
		}
		return obs;
	}
	
	/**
	 * 获取一个Observation对象中的数据映射到map
	 * @param obs
	 * @return
	 */
	private Map<String,String> getObseInfo(Observation obs){
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String id = obs.getId().getValueAsString();
		String version = obs.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString();
		String text = obs.getText().getDivAsString();
		String identifier = obs.getIdentifierFirstRep().getValue();
		String status = obs.getStatus();
		String code = obs.getCode().getCodingFirstRep().getCode()+"#"+obs.getCode().getCodingFirstRep().getDisplay();
		String sub = obs.getSubject().getReference().getValueAsString()+"#"+ obs.getSubject().getDisplay();
		String perf = obs.getPerformer().get(0).getReference().getValueAsString()+"#"+obs.getPerformer().get(0).getDisplay();
		String effect = ((PeriodDt)obs.getEffective()).getStart().toInstant()+","+((PeriodDt)obs.getEffective()).getEnd().toInstant();
		String issued = obs.getIssued().toInstant().toString();
		String valueQua = ((QuantityDt)obs.getValue()).getValue()+"";
		String unit = ((QuantityDt)obs.getValue()).getUnit();
		String range = obs.getReferenceRangeFirstRep().getLow().getValue()+"-"+obs.getReferenceRangeFirstRep().getHigh().getValue();
		String interp = obs.getInterpretation().getCodingFirstRep().getCode();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("uuid", UUID.randomUUID().toString());
		map.put("id", id);
		map.put("version", version);
		map.put("text", text);
		map.put("identifier", identifier);
		map.put("status", status);
		map.put("code", code);
		map.put("sub", sub);
		map.put("perf", perf);
		map.put("effect", effect);
		map.put("issued", issued);
		map.put("valueQua", valueQua);
		map.put("unit", unit);
		map.put("range", range);
		map.put("interp", interp);
		
		for(Entry<String,String> ent:map.entrySet()){
			System.out.println(ent.getKey()+":"+ent.getValue());
		}
		
		return map;
	}
	
	/**
	 * 提取map中的数据还原到一个Observation中
	 * @param map
	 * @return
	 * @throws ParseException 
	 */
	private Observation setObseInfo(Map<String,String> map) throws ParseException{
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String id = map.get("id".toUpperCase())+"";
		String text = map.get("text".toUpperCase())+"";
		String version = map.get("version".toUpperCase())+"";
		//String status = map.get("status".toUpperCase())+"";
		String identifier = map.get("identifier".toUpperCase());
		String code = map.get("code".toUpperCase())+"";
		String sub = map.get("sub".toUpperCase())+"";
		String perf = map.get("perf".toUpperCase())+"";
		String effect = map.get("effect".toUpperCase())+"";
		String issued = map.get("issued".toUpperCase())+"";
		String valueQua = map.get("valueQua".toUpperCase())+"";
		String unit = map.get("unit".toUpperCase())+"";
		String range = map.get("range".toUpperCase())+"";
		String interp = map.get("interp".toUpperCase())+"";
		
		Observation obs = new Observation();
		
		obs.setId(new IdDt(id).withVersion(version));
		
		ResourceMetadataMap resMap = new ResourceMetadataMap();
		//resMap.put(ResourceMetadataKeyEnum.VERSION, version);
		resMap.put(ResourceMetadataKeyEnum.UPDATED, new InstantDt(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(version)));
		obs.setResourceMetadata(resMap);
		
		NarrativeDt ndt = new NarrativeDt();
		ndt.setStatus(NarrativeStatusEnum.GENERATED);
		ndt.setDivAsString(text);
		obs.setText(ndt);
		
		List<IdentifierDt> identList = new ArrayList<IdentifierDt>();
		IdentifierDt idt = new IdentifierDt().setUse(IdentifierUseEnum.OFFICIAL).setValue(identifier);
		identList.add(idt);
		obs.setIdentifier(identList);
		
		obs.setStatus(ObservationStatusEnum.FINAL);
		
		String[] codes = code.split("#");
		CodeableConceptDt codeDt = new CodeableConceptDt();
		codeDt.addCoding().setSystem("http://loinc.org").setCode(codes[0]).setDisplay(codes[1]);
		obs.setCode(codeDt);
		
		String[] subs = sub.split("#");
		obs.setSubject(new ResourceReferenceDt().setReference(subs[0]).setDisplay(subs[1]));
		
		String[] perfs = perf.split("#");
		List<ResourceReferenceDt> resList = new ArrayList<ResourceReferenceDt>();
		resList.add(new ResourceReferenceDt().setReference(perfs[0]).setDisplay(perfs[1]));
		obs.setPerformer(resList);
		
		String[] effes = effect.split(",");
		PeriodDt pdt = new PeriodDt();
		pdt.setStart(new DateTimeDt(effes[0]));
		pdt.setEnd(new DateTimeDt(effes[1]));
		obs.setEffective(pdt);
		
		obs.setIssued(new InstantDt(issued));
		
		obs.setValue(new QuantityDt(QuantityComparatorEnum.GREATER_OR_EQUAL_TO,new Double(valueQua), "http://unitsofmeasure.org", unit).setCode(unit));
		
		obs.setInterpretation(new CodeableConceptDt("http://hl7.org/fhir/v2/0078", interp));
		
		String[] rangs = range.split("-");
		List<ReferenceRange> rangList = new ArrayList<Observation.ReferenceRange>();
		rangList.add(new ReferenceRange().setLow(new SimpleQuantityDt(new Double(rangs[0]), "http://unitsofmeasure.org", unit)).setHigh(new SimpleQuantityDt(new Double(rangs[1]), "http://unitsofmeasure.org", unit)));
		obs.setReferenceRange(rangList);
		
		return obs;
	}
}
