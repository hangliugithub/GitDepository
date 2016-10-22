package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.Observation;

public class TestDiagnosticReport {
	
	public Map<String,Object> getDiagnosticReportInfo(DiagnosticReport diag){
		String id = diag.getId().getValue();
		String version = diag.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString();
		String text = diag.getText().getDivAsString();
		String identifier = diag.getIdentifierFirstRep().getValue();
		String status = diag.getStatus();
		String code = diag.getCode().getCodingFirstRep().getCode()+"-"+diag.getCode().getText();
		List<IResource> list=diag.getContained().getContainedResources();
		
		List<Map<String,String>> obsList = new ArrayList<Map<String,String>>();
		for(IResource r : list){
			if(r instanceof Observation){
				Observation obs = (Observation)r;
				String oId = obs.getId().getValueAsString();
				String oStatus = obs.getStatus();
				String oCode = obs.getCode().getCodingFirstRep().getCode()+"#"+obs.getCode().getCodingFirstRep().getDisplay();
				String oSub = obs.getSubject().getReference().getValueAsString()+"#"+ obs.getSubject().getDisplay();
				String oPerf = obs.getPerformer().get(0).getReference().getValueAsString()+"#"+obs.getPerformer().get(0).getDisplay();
				String oValueQua = obs.getValue().toString();
				String oRange = obs.getReferenceRangeFirstRep().getLow().getValue()+"-"+obs.getReferenceRangeFirstRep().getHigh().getValue();
				String oInterp = obs.getInterpretation().getCodingFirstRep().getCode();
			}
		}
		
		List<ResourceReferenceDt> resultsList = diag.getResult();
		String results = "";
		for(ResourceReferenceDt rd : resultsList){
			results+=rd.getReference().getValueAsString()+",";
		}
		
		return null;
	}
	
}
