package mypackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class ConnectionData {	   
     Connection myCon;
	 Statement myStatement;   
	 public ConnectionData() {
		try{ 
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			System.out.println("成功加载驱动");			
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,"加载数据库驱动失败!","提示",JOptionPane.YES_OPTION ); 	         
		}
		try{
			//myCon=DriverManager.getConnection("jdbc:odbc:MyPhoneDataSource");
			myCon=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=MyDB","sa","123456");
			myStatement=myCon.createStatement();
			System.out.println("成功连接数据源");		
		}catch(SQLException sqe){
			//JOptionPane.showMessageDialog(null,"连接数据源失败!\n请建立名称为“MyPhoneDataSource”的数据源","提示",JOptionPane.YES_OPTION ); 	         
			sqe.printStackTrace();		
		}	 
	}
	 
	 //更新数据
	 public  int  UpdateData(String sql){
		 int result = 0;
		 try {
			result=myStatement.executeUpdate(sql);
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"sql语句执行失败！","错误提示",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return result;		 		 		 		 		 		 
	 }
	 public ResultSet QueryData(String sql){
		ResultSet result = null;
		 try {
			 
			 result=myStatement.executeQuery(sql);			 
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"sql语句执行失败！","错误提示",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();			
		}
		return result;				 		 
	 }	 
	 public void closeData()  
	 {		
			try { 		
				myStatement.close();
				myCon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }	 
}