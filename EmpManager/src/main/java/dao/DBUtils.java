package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
//import org.junit.Test;

public class DBUtils {
	
	private static BasicDataSource ds = new BasicDataSource();
	static{
		//String conPath="src\\main\\resources\\conf.properties";
		//String conPath = "WEB-INF\\classes\\conf.properties";
		String conPath="D:\\apache-tomcat-8.0.36\\wtpwebapps\\EmpManager\\WEB-INF\\classes\\conf.properties";
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(conPath));
			String driverName = prop.getProperty("driverName");
			String host = prop.getProperty("host");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			int maxActive = Integer.parseInt(prop.getProperty("maxActive"));
			long maxWait = Long.parseLong(prop.getProperty("maxWait"));
			
			//ds = new BasicDataSource();
			ds.setDriverClassName(driverName);
			ds.setUrl(host);
			ds.setUsername(username);
			ds.setPassword(password);
			ds.setMaxActive(maxActive);
			ds.setMaxWait(maxWait);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public static Connection getConnection(){
		try {
			//System.out.println(ds.getConnection());
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//	@Test
//	public void getConnectionTest(){
//		Connection conn = getConnection();
//		System.out.println(conn);
//	}
}
