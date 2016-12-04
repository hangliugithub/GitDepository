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
import ca.uhn.fhir.model.dstu2.resource.OperationOutcome;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;

@Controller
@RequestMapping("/diagnosticReport")
public class DiagnosticReportController {
	// http://127.0.0.1:8080/fhirserver/cpinfo
	// http://spark.furore.com/fhir
	// http://www.iuuui.cn/fhirserver/cpinfo

	// 设置测试服务器地址
	private String serverBase = "http://www.iuuui.cn/fhirserver/cpinfo";
	// 通过给定的地址创建一个客户端
	private IGenericClient client = Fhir.getClientFactory().newGenericClient(serverBase);

	public static final int JSON = 0;
	public static final int XML = 1;

	// 指定客户端与服务端的数据通信类型
	private int strType = XML;

	@ResponseBody
	@RequestMapping("/create")
	public JsonResult<String> create(HttpServletRequest req) {
		JsonResult<String> result = new JsonResult<String>();
		try {
			req.setCharacterEncoding("utf-8");
			String data = req.getParameter("data");
			System.out.println(data);
			DiagnosticReport diag = MyParser.parseToObject(data, "application/json", DiagnosticReport.class);
			MethodOutcome outcom = null;
			switch (strType) {
			case JSON:
				outcom = client.create().resource(diag).encodedJson().execute();
				break;
			case XML:
				outcom = client.create().resource(diag).encodedXml().execute();
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
		// client.setLogRequestAndResponse(true);
		try {
			DiagnosticReport diag = new DiagnosticReport();
			switch (strType) {
			case JSON:
				diag = client.read().resource(DiagnosticReport.class).withId(new IdDt(id)).preferResponseType(Observation.class)
				.encodedJson().execute();
				break;
			case XML:
				diag = client.read().resource(DiagnosticReport.class).withId(new IdDt(id)).preferResponseType(Observation.class)
				.encodedXml().execute();
				break;
			}
			System.out.println(diag.getContained().getContainedResources().size());
			String data = MyParser.parseToJSON(diag);
			System.out.println(data);
			result.setData(data);
			return result;
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			e.printStackTrace();
			return result;
		}
	}

	// {data:str}
	@RequestMapping("/update")
	@ResponseBody
	public JsonResult<Boolean> update(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("utf-8");
			String data = req.getParameter("data");
			// System.out.println(format);
			DiagnosticReport diag = MyParser.parseToObject(data, "application/json", DiagnosticReport.class);
			MethodOutcome outcom = null;
			System.out.println(diag.getId().getValue());
			switch (strType) {
			case 0:
				outcom = client.update().resource(diag).withId(diag.getId()).encodedJson().execute();
				break;
			case 1:
				outcom = client.update().resource(diag).withId(diag.getId()).encodedXml().execute();
				break;

			}
			String id = outcom.getId().getValue();
			System.out.println(id + ":更新成功");
			return new JsonResult<Boolean>(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Boolean>(e.getMessage());
		}
	}

	@RequestMapping("/del")
	@ResponseBody
	public JsonResult<Boolean> delete(String id) {
		System.out.println(id);
		try {
			IBaseOperationOutcome resp = client.delete().resourceById("DiagnosticReport", id).execute();
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
	public JsonResult<List<String>> history(String id) {
		System.out.println(id);
		try {
			List<String> list = new ArrayList<String>();
			Bundle res = null;
			switch (strType) {
			case JSON:
				res = client.history().onInstance(new IdDt("DiagnosticReport", id)).andReturnBundle(Bundle.class)
						.encodedJson().execute();
				break;
			case XML:
				res = client.history().onInstance(new IdDt("DiagnosticReport", id)).andReturnBundle(Bundle.class)
						.encodedXml().execute();
				break;
			}

			if (res != null)
				for (Entry en : res.getEntry()) {
					DiagnosticReport diag = (DiagnosticReport) en.getResource();
					String str = MyParser.parseToJSON(diag);
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
	public JsonResult<List<String>> search(String name) {
		System.out.println(name);
		List<String> list = new ArrayList<String>();
		ca.uhn.fhir.model.api.Bundle res = null;
		try {
			switch (strType) {
			case JSON:
				res = client.search().forResource(DiagnosticReport.class).where(new StringClientParam("code:text").matches().value(name))
						.encodedJson().execute();
				break;
			case XML:
				res = client.search().forResource(DiagnosticReport.class).where(new StringClientParam("code:text").matches().value(name))
						.encodedXml().execute();
				break;
			}
			if (res != null)
				for (BundleEntry b : res.getEntries()) {
					DiagnosticReport diag = (DiagnosticReport) b.getResource();
					String str = MyParser.parseToJSON(diag);
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
	public JsonResult<Boolean> changeType(int format) {
		try {

			if (format == JSON) {
				strType = JSON;
			} else if (format == XML) {
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
	public JsonResult<Boolean> serverBase(String serverBase) {
		try {
			client = Fhir.getClientFactory().newGenericClient(serverBase);
			return new JsonResult<Boolean>(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Boolean>(e.getMessage());
		}
	}
}
