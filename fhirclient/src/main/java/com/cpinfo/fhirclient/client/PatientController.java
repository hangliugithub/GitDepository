package com.cpinfo.fhirclient.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hl7.fhir.instance.model.api.IBaseOperationOutcome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cpinfo.fhirclient.util.Fhir;
import com.cpinfo.fhirclient.util.MyParser;

import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.OperationOutcome;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;

@Controller
@RequestMapping("/patient")
public class PatientController {
	// http://127.0.0.1:8080/fhirserver/cpinfo
	// http://spark.furore.com/fhir
	// http://www.iuuui.cn/fhirserver/cpinfo
	
	//设置测试服务器地址
	private String serverBase = "http://www.iuuui.cn/fhirserver/cpinfo";
	//通过给定的地址创建一个客户端
	private IGenericClient client = Fhir.getClientFactory()
			.newGenericClient(serverBase);
	
	public static final int JSON = 0;
	public static final int XML = 1;
	
	//指定客户端与服务端的数据通信类型
	private int strType = JSON;

	@ResponseBody
	@RequestMapping("/create")
	public JsonResult<String> create(HttpServletRequest req) {
		JsonResult<String> result = new JsonResult<String>();
		try {
			req.setCharacterEncoding("utf-8");
			String data = req.getParameter("data");
			System.out.println(data);
			Patient p = MyParser.parseToObject(data+"", "application/json", Patient.class);
			MethodOutcome outcom = null;

			switch (strType) {
			case JSON:
				outcom = client.create().resource(p).encodedJson().execute();
			break;
			case XML:
				outcom = client.create().resource(p).encodedXml().execute();
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
		try {
			Patient p = new Patient();
			switch(strType){
			case JSON:
				p = client.read()
				.resource(Patient.class).withId(new IdDt(id))
				.encodedJson().execute();
				break;
			case XML:
				p = client.read()
				.resource(Patient.class).withId(new IdDt(id))
				.encodedXml().execute();
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
			Patient p = MyParser.parseToObject(data,
					"application/json", Patient.class);
			MethodOutcome outcom = null;
			System.out.println(MyParser.parseToJSON(p));
			switch (strType) {
			case 0:
				outcom = client.update().resource(p)
				.withId(p.getId()).encodedJson().execute();
				break;
			case 1:
				outcom = client.update().resource(p)
				.withId(p.getId()).encodedXml().execute();
				break;
			}
			String id = outcom.getId().getValue();
			System.out.println(id+":更新成功");
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
			IBaseOperationOutcome resp= client.delete()
					.resourceById("Patient", id).execute();
			if (resp != null) {
			   OperationOutcome outcome = (OperationOutcome) resp;
			   System.out.println(outcome
					   .getIssueFirstRep().getDiagnosticsElement()
					   .getValue());
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
				res = client.history()
				.onInstance(new IdDt("Patient",id))
				.andReturnBundle(Bundle.class)
				.encodedJson().execute();
				break;
			case XML:
				res = client.history()
				.onInstance(new IdDt("Patient",id))
				.andReturnBundle(Bundle.class)
				.encodedXml().execute();
				break;
			}
			if(res!=null)
			for(Entry en: res.getEntry()){
				Patient p = (Patient) en.getResource();
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
				res = client.search()
				.forResource(Patient.class)
				.where(Patient.NAME.matches().value(name))
				.encodedJson().execute();
				break;
			case XML:
				res = client.search()
				.forResource(Patient.class)
				.where(Patient.NAME.matches().value(name))
				.encodedXml().execute();
				break;
			}
			if(res!=null)
			for(BundleEntry b: res.getEntries()){
				Patient p = (Patient) b.getResource();
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
	public JsonResult<Boolean> changeType(HttpServletRequest req, int format){
		try {
			
			if(format==JSON){
				strType = JSON;
			}else if(format==XML){
				strType = XML;
			}
			req.getSession().setAttribute("type", strType);
			return new JsonResult<Boolean>(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Boolean>(e.getMessage());
		}
	}
	
	@RequestMapping("/changeServerBase")
	@ResponseBody
	public JsonResult<Boolean> serverBase(HttpServletRequest req, String serverBase){
		System.out.println(serverBase);
		try {
			client = Fhir.getClientFactory().newGenericClient(serverBase);
			req.getSession().setAttribute("server", serverBase);
			return new JsonResult<Boolean>(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Boolean>(e.getMessage());
		}
	}
	
	@RequestMapping("/getContext")
	@ResponseBody
	public JsonResult<Map<String,Object>> getContext(HttpServletRequest req){
		String server = req.getSession().getAttribute("server")+"";
		if("null".equals(server)||"".equals(server)){
			server = "http://www.iuuui.cn/fhirserver/cpinfo";
		}
		int type = JSON;
		try {
			type = new Integer(req.getSession().getAttribute("type")+"");
		} catch (Exception e) {
			type = JSON;
		}
		System.out.println(server);
		System.out.println(type);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("server",server);
		map.put("type", type);
		return new JsonResult<Map<String,Object>>(map);
	}
	
	
}











