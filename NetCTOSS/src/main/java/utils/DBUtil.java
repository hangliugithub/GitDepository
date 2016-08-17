package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;


public class DBUtil {
	
	private static BasicDataSource ds = new BasicDataSource();
	static{
		//String conPath="src\\main\\resources\\conf.properties";
		//String conPath = "WEB-INF\\classes\\conf.properties";
		//String conPath="D:\\apache-tomcat-8.0.36\\wtpwebapps\\EmpManager\\WEB-INF\\classes\\conf.properties";
		Properties prop = new Properties();
		try {
			//通过ClassLoader从classes下读取properties文件，web项目必须这样
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("conf.properties"));
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
	public static Connection getConnection() throws SQLException{
		try {
			//System.out.println(ds.getConnection());
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
			//return null;
		}
	}
	
	public static void close(Connection conn) throws SQLException{
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	@Test
	public void getConnectionTest() throws SQLException{
		Connection conn = getConnection();
		System.out.println(conn);
		close(conn);
	}
}
