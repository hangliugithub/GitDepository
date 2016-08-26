package cont;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalcController {
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping("/calc.do")
	public String calc(Info info,ModelMap mm){
		//System.out.println(info);
		if(info!=null){
			double bmi = info.getWeight()/info.getHeight()/info.getHeight();
			
			if(bmi<=18.4){
				info.setType("ƫ��");
			}else if(18.5<=bmi && bmi<=23.9){
				info.setType("����");
			}else if(24.0<=bmi && bmi<=27.9){
				info.setType("����");
			}else{
				info.setType("����");
			}
			info.setBmi(bmi);
			mm.put("info", info);
		}
		return "index";
	}
}
