package com.cpinfo.hl7.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cpinfo.hl7.service.PatientService;
import com.cpinfo.hl7.util.MyParser;

import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.dstu2.resource.Patient;

@Controller
@RequestMapping("/Patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	//创建资源
	@RequestMapping( method =RequestMethod.POST)
	public void creatRes(HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			System.out.println(contentType);
			String str = readRequestBody(req);
			
			if((str==null||str.length()==0) || (contentType==null||contentType.length()==0)){
				throw new RuntimeException("字符串或字符串类型不能为空");
			}
			
			Patient p = patientService.createPatient(MyParser.parseToObject(str, contentType, Patient.class));
			
			res.setStatus(201);
			res.addHeader("Location", req.getRequestURL()+"/"+p.getId().getValue());
			res.addHeader("Last-Modified",p.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			//res.addHeader("access-control-allow-origin", "*");
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	//根据id读取资源
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public void getRes(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			System.out.println(contentType);
			//Enumeration heards = req.getHeaderNames();
			//for
			
			Patient p = patientService.readPatient(id);
			String result = "";
			if("application/json".equalsIgnoreCase(contentType)){
				result = MyParser.parseToJSON(p);
			}else if("application/xml".equalsIgnoreCase(contentType)){
				result = MyParser.parseToXML(p);
			}else{
				res.setStatus(400);
				return;
			}
			//System.out.println(result);
			res.setCharacterEncoding("utf-8");
			res.addHeader("Last-Modified",p.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			res.setContentType("application/json");
			res.addHeader("access-control-allow-origin", "*");
			res.addHeader("access-control-allow-origin", "POST,GET,PUT,OPTIONS,DELETE");
//			res.addHeader("access-control-allow-origin", "x-requested-with,content-type");
			//res.setStatus(200);
			PrintWriter writer = res.getWriter();
			writer.println(result);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
	}
	//根据姓名搜索资源
	@RequestMapping(method=RequestMethod.GET)
	public void findRes(HttpServletRequest req,HttpServletResponse res,String name){
		try {
			String contentType = req.getContentType();
			List<Patient> list = patientService.findPatientByName(name);
			String result = "";
			if("application/xml".equalsIgnoreCase(contentType)){
				for(Patient p:list){
					result += MyParser.parseToXML(p)+",";
				}
			}else if("application/json".equalsIgnoreCase(contentType)){
				for(Patient p:list){
					result += MyParser.parseToJSON(p)+",";
				}
			}
			
			res.setCharacterEncoding("utf-8");
			PrintWriter writer = res.getWriter();
			writer.println(result);
			writer.close();
			
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	//更新资源
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public void updRes(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			String str = readRequestBody(req);
			
			if((str==null||str.length()==0) || (contentType==null||contentType.length()==0)){
				throw new RuntimeException("字符串或字符串类型不能为空");
			}
			
			Patient p = patientService.updatePatient(MyParser.parseToObject(str, contentType, Patient.class));
			
			//Patient p = patientService.updatePatient(id, str, contentType);
			res.addHeader("Location", req.getRequestURL()+"");
			res.addHeader("Last-Modified",p.getResourceMetadata().get(ResourceMetadataKeyEnum.VERSION).toString());
			
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	//删除资源
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void delRes(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			System.out.println(id);
			int num = patientService.delPatient(id);
			if(num<=0){
				res.setStatus(404);
			}
			res.setStatus(410);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取历史版本
	@RequestMapping(value="/{id}/_history",method=RequestMethod.GET)
	public void getResHistory(@PathVariable String id,HttpServletRequest req,HttpServletResponse res){
		try {
			String contentType = req.getContentType();
			List<Patient> list = patientService.findHisPatient(id);
			String result = "";
			if("application/xml".equalsIgnoreCase(contentType)){
				for(Patient p:list){
					result += MyParser.parseToXML(p)+",";
				}
			}else if("application/json".equalsIgnoreCase(contentType)){
				for(Patient p:list){
					result += MyParser.parseToJSON(p)+",";
				}
			}
			
			res.setCharacterEncoding("utf-8");
			PrintWriter writer = res.getWriter();
			writer.println(result);
			writer.close();
			
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	
	public static String readRequestBody(HttpServletRequest req) throws IOException{
		req.setCharacterEncoding("utf-8");
		String str = "";
		BufferedReader reader = req.getReader();
		String tmp;
		while((tmp=reader.readLine())!=null){
			str+=tmp;
		}
		reader.close();
		return str;
	}
	
}
