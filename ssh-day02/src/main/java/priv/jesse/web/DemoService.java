package priv.jesse.web;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

	public DemoService() {
	}
	
	public String test(){
		return "hello world!";
	}

}
