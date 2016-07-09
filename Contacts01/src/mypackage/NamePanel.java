package mypackage;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class NamePanel extends JPanel implements ListSelectionListener{
	private static final long serialVersionUID = 1L;
	static JList<String> nameList;
	static String[] name;
	private static ResultSet result1,result2,result3;
	static int length=0;
	JScrollPane scrollPane;
	public NamePanel(){
		setLayout(new BorderLayout());
		nameList = new JList<String>(new DefaultListModel<String>());
		nameList.setListData(getAllName());//��ʾ�б�����ϵ��,name�ǳ���getAllName()ʱ��ȷ����
		nameList.setBorder(BorderFactory.createTitledBorder("�ҵ���ϵ��"));
		nameList.setFixedCellWidth(120);
		scrollPane=new JScrollPane(nameList,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);	 
		add(scrollPane);
		nameList.addListSelectionListener(this);	
	}	
    public void valueChanged(ListSelectionEvent arg0) {
    	String str=getSelectedText();
		if(str==null){
			System.out.println("return null");
			return ;
		}
    	String sql="select * from phonetable where name='"+str+"'";
    	ConnectionData conObject = new ConnectionData(); //��������	
    	try {
    		result1 = conObject.QueryData(sql);
    		result1.next();                              ///�����ӵ�ǰλ����ǰ��һ��?????
			String str1=result1.getString(1);  	
			String str2=result1.getString(2);
			String str3=result1.getString(3);
			String str4=result1.getString(4);
			String str5=result1.getString(5);
			String str6=result1.getString(6);
			String str7=result1.getString(7);
			String str8=result1.getString(8);
			String str9=result1.getString(9);
			
			MainFrame.centerPanel1.setFieldText(str1,str2,str3,str4,str5,str6,str7,str8,str9);//
			MainFrame.centerPanel1.setFieldEditable(false,false);/////////////�����ı���༭״̬//////////
			ButtonPanel.modifybutton.setText("�޸�");
		} catch (SQLException e) {e.printStackTrace();}
		   conObject.closeData();//�ر�����	
    }
	public static  String[] getAllName()   {
		int i = 0;
		ConnectionData conObject = new ConnectionData(); //��������
		String sql2 = "SELECT * FROM phonetable order by name ";
		String sql3 = "SELECT count(*) as aa   FROM phonetable ";
		result3 = conObject.QueryData(sql3);	
			try {
				result3.next();
				length=result3.getInt(1);
				System.out.println("length="+length);//////////////////////////length ���ݿ�������
			} catch (SQLException e1) {			
				e1.printStackTrace();
			}						 	
		name = new String[length];			
		result2 = conObject.QueryData(sql2);
		try {
			while (result2.next()) {
					name[i++] = result2.getString(1);					
				}
		} catch (SQLException e) { 
			e.printStackTrace();
		}			
		conObject.closeData();/// �Ͽ�����
		return name;
	}
	public static JList<String> getNameList() {
		return nameList;
	}
	public static String getSelectedText(){
		String str =null;
		if(nameList.getSelectedIndex()!=-1)
		{
			str = name[nameList.getSelectedIndex()];
			System.out.println("ѡ��i "+nameList.getSelectedIndex());//////////////////////ѡ��i 
		}
		return str;
	}	
	public static void refresh(){
		name = getAllName();
		nameList=getNameList(); 
		nameList.setListData(name);         //ˢ���б�
		MainFrame.centerPanel1.setFieldText("","","","","","","","","");
		
	}
		
}