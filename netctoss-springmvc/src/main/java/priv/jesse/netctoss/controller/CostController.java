package priv.jesse.netctoss.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import priv.jesse.netctoss.entity.Cost;
import priv.jesse.netctoss.service.CostService;

@Controller
public class CostController {
	@Resource(name="costService")
	private CostService service;
	
	@RequestMapping("/findCost.do")
	public String listAllCost(HttpServletRequest req){
		try{
		List<Cost> list = service.listAll();
		req.setAttribute("costs", list);
		return "cost/find";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	@RequestMapping("/detialCost.do")
	public String detialCost(HttpServletRequest req){
		String costId = req.getParameter("id");
		try{
			Cost cost  = service.detialCost(costId);
			req.setAttribute("cost", cost);
			return "cost/detial";
		}catch(Exception e){
			return "error";
		}
	}
}
