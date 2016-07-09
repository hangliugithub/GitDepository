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
			System.out.println("�ɹ���������");			
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,"�������ݿ�����ʧ��!","��ʾ",JOptionPane.YES_OPTION ); 	         
		}
		try{
			//myCon=DriverManager.getConnection("jdbc:odbc:MyPhoneDataSource");
			myCon=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=MyDB","sa","123456");
			myStatement=myCon.createStatement();
			System.out.println("�ɹ���������Դ");		
		}catch(SQLException sqe){
			//JOptionPane.showMessageDialog(null,"��������Դʧ��!\n�뽨������Ϊ��MyPhoneDataSource��������Դ","��ʾ",JOptionPane.YES_OPTION ); 	         
			sqe.printStackTrace();		
		}	 
	}
	 
	 //��������
	 public  int  UpdateData(String sql){
		 int result = 0;
		 try {
			result=myStatement.executeUpdate(sql);
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"sql���ִ��ʧ�ܣ�","������ʾ",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return result;		 		 		 		 		 		 
	 }
	 public ResultSet QueryData(String sql){
		ResultSet result = null;
		 try {
			 
			 result=myStatement.executeQuery(sql);			 
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"sql���ִ��ʧ�ܣ�","������ʾ",JOptionPane.ERROR_MESSAGE);
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