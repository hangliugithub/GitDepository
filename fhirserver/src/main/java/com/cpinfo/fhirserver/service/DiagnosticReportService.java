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

import com.cpinfo.fhirserver.dao.DiagnosticReportDao;

import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.NarrativeDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.valueset.DiagnosticReportStatusEnum;
import ca.uhn.fhir.model.dstu2.valueset.IdentifierUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.NarrativeStatusEnum;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;

@Service("diagService")
public class DiagnosticReportService {
	
	@Autowired
	private DiagnosticReportDao diagDao;
	@Autowired
	private ObservationService obsService;
	
	/**
	 * 新增一个DiagnosticReport
	 * @param str
	 * @param strType
	 * @return
	 */
	public DiagnosticReport createDiag(DiagnosticReport diag){
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		diag.setResourceMetadata(metaMap);
		
		Map<String,String> diagMap = getDiagInfo(diag);
		diagDao.creat(diagMap);
		diag.setId(diagMap.get("id"));
		return diag;
	}
	
	/**
	 * 通过id查找一个DiagnosticReport
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	public DiagnosticReport readDiag(String id) throws ParseException{
		Map<String,String> map = diagDao.findById(id);
		if(map==null||map.isEmpty()){
			throw new RuntimeException("未找到");
		}
		
		//去查找该检验报告包含的observation
		String results = map.get("results".toUpperCase())+"";
		String[] res = results.split("#");
		//List<IResource> observations = new ArrayList<IResource>();
		DiagnosticReport diag = setDiagInfo(map);
		for(String r:res){
			System.out.println(r);
			Observation obs = obsService.readObservation(r);
			//observations.add(obs);
			obs.setId(obs.getId().getValue().split("/")[0]);
			//必须引用localID，以#开头
			diag.addResult().setReference("#"+obs.getId().getValue().split("/")[0]);
			diag.getContained().getContainedResources().add(obs);
			//System.out.println(MyParser.parseToXML(obs));
			//System.out.println(obs.getId().getValue());
			
		}
//		ContainedDt cdt = new ContainedDt();
//		cdt.setContainedResources(observations);
//		diag.setContained(cdt);
		//diag.getContained().setContainedResources(observations);
		
//		for(IResource i: diag.getContained().getContainedResources()){
//			System.out.println(MyParser.parseToJSON(i));
//		}
		
		return diag;
	}
	/**
	 * 更新
	 * @param id
	 * @param str
	 * @param strType
	 * @return
	 */
	public DiagnosticReport updateDiagnosticReport(DiagnosticReport diag){
//		DiagnosticReport diag = null;
//		diag = MyParser.parseToObject(str, strType, DiagnosticReport.class);
//		diag.setId(id);
		ResourceMetadataMap metaMap = new ResourceMetadataMap();
		metaMap.put(ResourceMetadataKeyEnum.VERSION, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()));
		diag.setResourceMetadata(metaMap);
		Map<String,String> diagMap = getDiagInfo(diag);
		diagDao.setActive(diag.getId().getValueAsString());
		diagDao.update(diagMap);
		return diag;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delDiag(String id){
		return diagDao.delById(id);
	}
	/**
	 * 所有历史版本
	 * @param id
	 * @return
	 * @throws ParseException
	 */
	public List<DiagnosticReport> findHisDiag(String id) throws ParseException{
		List<Map<String,String>> diagList = diagDao.findHisById(id);
		if(diagList==null||diagList.isEmpty()){
			throw new RuntimeException("未找到");
		}
		List<DiagnosticReport> diags = new ArrayList<DiagnosticReport>();
		for(Map<String,String> map:diagList){
			
			//去查找该检验报告包含的observation
			String results = map.get("results".toUpperCase())+"";
			String[] res = results.split("#");
			//List<IResource> observations = new ArrayList<IResource>();
			DiagnosticReport diag= setDiagInfo(map);
			for(String r:res){
				Observation obs = obsService.readObservation(r);
				//observations.add(obs);
				obs.setId(obs.getId().getValue().split("/")[0]);
				//必须引用localID，以#开头
				diag.addResult().setReference("#"+obs.getId().getValue().split("/")[0]);
				diag.getContained().getContainedResources().add(obs);
			}
			//ContainedDt cdt = new ContainedDt();
			//cdt.setContainedResources(observations);
			//diag.setContained(cdt);
			
			diags.add(diag);
		}
		return diags;
	}
	/**
	 * 通过报告名查找
	 * @param name
	 * @return
	 * @throws ParseException
	 */
	public List<DiagnosticReport> findByName(String name) throws ParseException{
		List<Map<String,String>> diagList = diagDao.findByName(name);
		if(diagList==null||diagList.isEmpty()){
			throw new RuntimeException("未找到");
		}
		List<DiagnosticReport> diags = new ArrayList<DiagnosticReport>();
		for(Map<String,String> map:diagList){
			//去查找该检验报告包含的observation
			String results = map.get("results".toUpperCase())+"";
			String[] res = results.split("#");
			//List<IResource> observations = new ArrayList<IResource>();
			DiagnosticReport diag= setDiagInfo(map);
			for(String r:res){
				Observation obs = obsService.readObservation(r);
				obs.setId(obs.getId().getValue().split("/")[0]);
				//必须引用localID，以#开头
				diag.addResult().setReference("#"+obs.getId().getValue().split("/")[0]);
				diag.getContained().getContainedResources().add(obs);
			}
			
			diags.add(diag);
		}
		return diags;
	}
	
	/**
	 * 将DiagnosticReport中的信息映射到map中
	 * @param diag
	 * @return
	 */
	private Map<String,String> getDiagInfo(DiagnosticReport diag){
		
		String id = diag.getId().getValue();
		String version = diag.getResourceMetadata()
				.get(ResourceMetadataKeyEnum.VERSION).toString();
		String text = diag.getText().getDivAsString()+"";
		String identifier = diag.getIdentifierFirstRep().getValue();
		String status = diag.getStatus();
		String code = diag.getCode().getCodingFirstRep()
				.getCode()+"#"+diag.getCode()
				.getCodingFirstRep().getDisplay();
		String sub = diag.getSubject()
				.getReference().getValueAsString()+"#"
				+ diag.getSubject().getDisplay();
		String effect = ((DateTimeDt)diag.getEffective())
				.getValueAsString();
		String issued = diag.getIssued().toInstant().toString();
		String perf = diag.getPerformer()
				.getReference()
				.getValueAsString()+"#"
				+diag.getPerformer()
				.getDisplay();
		String results = "";
		List<ResourceReferenceDt> resultList = diag.getResult();
		for(ResourceReferenceDt r : resultList){
			results += r.getReference().getValueAsString()+"#";
		}
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("uuid", UUID.randomUUID().toString());
		map.put("id", id);
		map.put("version", version);
		map.put("text", text);
		map.put("identifier", identifier);
		map.put("status", status);
		map.put("code", code);
		map.put("sub", sub);
		map.put("effect", effect);
		map.put("issued", issued);
		map.put("perf", perf);
		map.put("results", results);
		
		for(Entry<String,String> ent:map.entrySet()){
			System.out.println(ent.getKey()+":"+ent.getValue());
		}
		
		return map;
	}
	
	/**
	 * 将map中的信息还原到DiagnosticReport中
	 * @param map
	 * @return
	 * @throws ParseException 
	 */
	private DiagnosticReport setDiagInfo(Map<String,String> map) throws ParseException{
		
		for(Entry<String,String> ent:map.entrySet()){
			System.out.println(ent.getKey()+":"+ent.getValue());
		}
		
		String id = map.get("id".toUpperCase())+"";
		String version = map.get("version".toUpperCase())+"";
		String text = map.get("text".toUpperCase())+"";
		String identifier = map.get("identifier".toUpperCase())+"";
		//String status = map.get("status".toUpperCase())+"";
		String code = map.get("code".toUpperCase())+"";
		String sub = map.get("sub".toUpperCase())+"";
		String effect = map.get("effect".toUpperCase())+"";
		String issued = map.get("issued".toUpperCase())+"";
		String perf = map.get("perf".toUpperCase())+"";
		//String results = map.get("results".toUpperCase())+"";
		
		DiagnosticReport diag = new DiagnosticReport();
		diag.setId(new IdDt(id).withVersion(version));
		ResourceMetadataMap resMap = new ResourceMetadataMap();
		//resMap.put(ResourceMetadataKeyEnum.VERSION, version);
		resMap.put(ResourceMetadataKeyEnum.UPDATED,
				new InstantDt(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").parse(version)));
		diag.setResourceMetadata(resMap);
		NarrativeDt ndt = new NarrativeDt();
		ndt.setStatus(NarrativeStatusEnum.GENERATED);
		ndt.setDivAsString(text);
		diag.setText(ndt);
		List<IdentifierDt> identList = new ArrayList<IdentifierDt>();
		IdentifierDt idt = new IdentifierDt()
				.setUse(IdentifierUseEnum.OFFICIAL)
				.setValue(identifier)
				.setSystem("http://acme.com/lab/reports");
		identList.add(idt);
		diag.setIdentifier(identList);
		diag.setStatus(DiagnosticReportStatusEnum.FINAL);
		String[] codes = code.split("#");
		CodeableConceptDt codeDt = new CodeableConceptDt();
		codeDt.setText(codes[1]);
		codeDt.addCoding().setSystem("http://loinc.org")
			.setCode(codes[0]).setDisplay(codes[1]);
		diag.setCode(codeDt);
		String[] subs = sub.split("#");
		diag.setSubject(new ResourceReferenceDt()
				.setReference(subs[0])
				.setDisplay(subs[1]));
		diag.setEffective(new DateTimeDt(effect));
		diag.setIssued(new InstantDt(issued));
		String[] perfs = perf.split("#");
		diag.setPerformer(new ResourceReferenceDt()
				.setReference(perfs[0])
				.setDisplay(perfs[1]));
		
//		String[] result = results.split("#");
//		List<ResourceReferenceDt> rdtList = new ArrayList<ResourceReferenceDt>();
//		for(String r : result){
//			rdtList.add(new ResourceReferenceDt(r));
//		}
//		diag.setResult(rdtList);
		
		//diag.setContained(new ContainedDt().setContainedResources(List<IResource>));
		return diag;
	}
}
