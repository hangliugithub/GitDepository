package com.cpinfo.fhirclient.util;

import org.hl7.fhir.instance.model.api.IBaseResource;

import ca.uhn.fhir.parser.IParser;

public class MyParser {
	private static IParser jsonParser;
	private static IParser xmlParser;
	static{
		jsonParser = Fhir.getCtx().newJsonParser().setPrettyPrint(true);
		xmlParser = Fhir.getCtx().newXmlParser().setPrettyPrint(true);
	}
	
	public static <T extends IBaseResource> T parseToObject(String str,String contentType,Class<T> resType){
		if("application/json".equalsIgnoreCase(contentType)){
			//IParser	parser = ctx.forDstu2().newJsonParser();
			return jsonParser.parseResource(resType, str);
		}
		
		if("application/xml".equalsIgnoreCase(contentType)){
			//IParser	parser = ctx.forDstu2().newXmlParser();
			return xmlParser.parseResource(resType, str);
		}
		return null;
	}

	public static String parseToJSON(IBaseResource res){
		//IParser	parser = ctx.forDstu2().newJsonParser().setPrettyPrint(true);
		return jsonParser.encodeResourceToString(res);
	}
	
	public static String parseToXML(IBaseResource res){
		//IParser	parser = ctx.forDstu2().newXmlParser().setPrettyPrint(true);
		return xmlParser.encodeResourceToString(res);
	}
}
