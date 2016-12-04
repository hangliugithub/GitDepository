package com.cpinfo.fhirclient.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cpinfo.fhirclient.util.Fhir;
import com.cpinfo.fhirclient.util.MyParser;

import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.Condition;
import ca.uhn.fhir.model.dstu2.resource.OperationOutcome;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;

@Controller
@RequestMapping("/condition")
public class ConditionController {

	// http://127.0.0.1:8080/fhirserver/cpinfo
	// http://www.iuuui.cn/fhirserver/cpinfo
	// http://spark.furore.com/fhir
	
	//���ò��Է�������ַ
	private String serverBase = "http://www.iuuui.cn/fhirserver/cpinfo";
	//ͨ�������ĵ�ַ����һ���ͻ���
	private IGenericClient client = Fhir.getClientFactory().newGenericClient(serverBase);
	
	public static final int JSON = 0;
	public static final int XML = 1;
	
	//ָ���ͻ��������˵�����ͨ������
	private int strType = XML;

	@ResponseBody
	@RequestMapping("/create")
	public JsonResult<String> create(HttpServletRequest req) {
		JsonResult<String> result = new JsonResult<String>();
		try {
			req.setCharacterEncoding("utf-8");
			String data = req.getParameter("data");
			System.out.println(data);
			Condition condition = MyParser.parseToObject(data+"", "application/json", Condition.class);
			MethodOutcome outcom = null;

			switch (strType) {
			case JSON:
				outcom = client.create().resource(condition).encodedJson().execute();
			break;
			case XML:
				outcom = client.create().resource(condition).encodedXml().execute();
				break;

			}

			String id = outcom.getId().getValue();
			System.out.println(id);
			result.setData(id);
			return result;
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			e.printStackTrace();
			return result;
		}
	}
	
	@ResponseBody
	@RequestMapping("/read")
	public JsonResult<String> read(String id) {
		JsonResult<String> result = new JsonResult<String>();
		//client.setLogRequestAndResponse(true);
		try {
			Condition p = new Condition();
			switch(strType){
			case JSON:
				p = client.read().resource(Condition.class).withId(new IdDt(id)).encodedJson().execute();
				break;
			case XML:
				p = client.read().resource(Condition.class).withId(new IdDt(id)).encodedXml().execute();
				break;
			}
			String data = MyParser.parseToJSON(p);
			System.out.println(data);
			result.setData(data);
			return result;
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			e.printStackTrace();
			return result;
		}
	}

	//{data:str}
	@RequestMapping("/update")
	@ResponseBody
	public JsonResult<Boolean> update(HttpServletRequest req){
		try {
			req.setCharacterEncoding("utf-8");
			String data = req.getParameter("data");
			// System.out.println(format);
			Condition condition = MyParser.parseToObject(data, "application/json", Condition.class);
			MethodOutcome outcom = null;
			System.out.println(condition.getId().getValue());
			switch (strType) {
			case 0:
				outcom = client.update().resource(condition).withId(condition.getId()).encodedJson().execute();
				break;
			case 1:
				outcom = client.update().resource(condition).withId(condition.getId()).encodedXml().execute();
				break;

			}
			String id = outcom.getId().getValue();
			System.out.println(id+":���³ɹ�");
			return new JsonResult<Boolean>(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Boolean>(e.getMessage());
		}
	}
	@RequestMapping("/del")
	@ResponseBody
	public JsonResult<Boolean> delete(String id){
		System.out.println(id);
		try {
			IBaseOperationOutcome resp= client.delete().resourceById("Condition", id).execute();
			if (resp != null) {
			   OperationOutcome outcome = (OperationOutcome) resp;
			   System.out.println(outcome.getIssueFirstRep().getDiagnosticsElement().getValue());
			}
			 return new JsonResult<Boolean>(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Boolean>(e.getMessage());
		}
	}
	
	@RequestMapping("/his")
	@ResponseBody
	public JsonResult<List<String>> history(String id){
		System.out.println(id);
		try {
			List<String> list = new ArrayList<String>();
			Bundle res = null;
			switch(strType){
			case JSON:
				res = client.history().onInstance(new IdDt("Condition",id)).andReturnBundle(Bundle.class).encodedJson().execute();
				break;
			case XML:
				res = client.history().onInstance(new IdDt("Condition",id)).andReturnBundle(Bundle.class).encodedXml().execute();
				break;
			}
			
			if(res!=null)
			for(Entry en: res.getEntry()){
				Condition p = (Condition) en.getResource();
				String str = MyParser.parseToJSON(p);
				System.out.println(str);
				list.add(str);
			}
			return new JsonResult<List<String>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<String>>(e.getMessage());
		}
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public JsonResult<List<String>> search(String name){
		System.out.println(name);
		List<String> list = new ArrayList<String>();
		ca.uhn.fhir.model.api.Bundle res = null;
		try {
			switch(strType){
			case JSON:
				res = client.search().forResource(Condition.class).where(new StringClientParam("code:text").matches().value(name)).encodedJson().execute();
				break;
			case XML:
				res = client.search().forResource(Condition.class).where(new StringClientParam("code:text").matches().value(name)).encodedXml().execute();
				break;
			}
			if(res!=null)
			for(BundleEntry b: res.getEntries()){
				Condition p = (Condition) b.getResource();
				String str = MyParser.parseToJSON(p);
				System.out.println(str);
				list.add(str);
					
			}
			return new JsonResult<List<String>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<String>>(e.getMessage());
		}
	}
	
	@RequestMapping("/changeType")
	@ResponseBody
	public JsonResult<Boolean> changeType(int format){
		try {
			
			if(format==JSON){
				strType = JSON;
			}else if(format==XML){
				strType = XML;
			}
			return new JsonResult<Boolean>(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Boolean>(e.getMessage());
		}
	}
	
	@RequestMapping("/changeServerBase")
	@ResponseBody
	public JsonResult<Boolean> serverBase(String serverBase){
		try {
			System.out.println(serverBase);
			client = Fhir.getClientFactory().newGenericClient(serverBase);
			return new JsonResult<Boolean>(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Boolean>(e.getMessage());
		}
	}
}
