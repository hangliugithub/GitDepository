package priv.jesse.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class SwitchAction {

	int num;
	
	public void setNum(int num){
		this.num = num;
	}
	
	public SwitchAction() {}
	
	public String doSwitch(){
		return Integer.toString(num);
	}
	

}
