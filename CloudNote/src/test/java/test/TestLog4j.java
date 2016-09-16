package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestLog4j {
	
	private static final Logger logger = LogManager.getLogger("TestLog4j");
	
	@Test
	public void test1() {
		logger.debug("This is debug message");
		logger.info("This is info");
		logger.error("This is error message");
	}

}
