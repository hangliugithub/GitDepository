package mypackage;

import java.awt.GridLayout;
import javax.swing.*;

public class CenterPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel,phonelabel1,phonelabel2,homeLabel,birthLabel,mailLabel,qqLabel,workunitLabel,addressLabel;
	private JTextField  nameField,phoneField1, phoneField2, homeField, birthField, mailField, qqField, workunitField,addressField; 	 	
	public CenterPanel(){
		this.setLayout(new GridLayout(9,2,-100,5));	
		
		nameLabel = new JLabel("姓名：");
		nameField = new JTextField(10);	//数据表该列的数据类型要为char（）不然文本框显示为空！
		add(nameLabel);
		add(nameField);
		
		phonelabel1 = new JLabel("手机号码1：");
		phoneField1 = new JTextField(15);		
		phoneField1.setDocument(new NumOnly());
		add(phonelabel1);
		add(phoneField1);
		
		phonelabel2 = new JLabel("手机号码2：");
		phoneField2 = new JTextField(15);
		phoneField2.setDocument(new NumOnly());
		add(phonelabel2);
		add(phoneField2);
		
		homeLabel = new JLabel("家中号码：");
		homeField = new JTextField(11);
		homeField.setDocument(new NumOnly());
		add(homeLabel);
		add(homeField);
		
		birthLabel=new JLabel("生日：");
		birthField=new JTextField(8);
		add(birthLabel);
		add(birthField);
		
		mailLabel = new JLabel("E-mail：");
		mailField = new JTextField(15);
		add(mailLabel);
		add(mailField);
		
		qqLabel = new JLabel("QQ：");
		qqField = new JTextField(10);
		qqField.setDocument(new NumOnly());
		add(qqLabel);
		add(qqField);
		
		workunitLabel = new JLabel("工作地点：");
		workunitField = new JTextField(15);
		add(workunitLabel);
		add(workunitField);
	
		addressLabel = new JLabel("家庭住址：");
		addressField = new JTextField(15);
		add(addressLabel);
		add(addressField);
	}
	public void setFieldEditable(boolean a,boolean b){
		nameField.setEditable(b);
		phoneField1.setEditable(a);
		phoneField2.setEditable(a);
		
		homeField.setEditable(a);
		birthField.setEditable(a);
		mailField.setEditable(a);
		
		qqField.setEditable(a);
		workunitField.setEditable(a);
		addressField.setEditable(a);
	}
	public String getNameText(){
		return nameField.getText();
	}
	public String getPhonenum1Text(){
		return phoneField1.getText();
	}
	public String getPhonenum2Text(){
		return phoneField2.getText();
	}
	public String getHomenumText(){
		return homeField.getText();
	}
	public String getBirthText(){
		return birthField.getText();
	}
	public String getMailText(){
		return mailField.getText();
	}
	public String getQQText(){
		return qqField.getText();
	}
	public String getWorkunitText(){
		return workunitField.getText();
	}
	public String getAddressText(){
		return addressField.getText();
	}
	public void setFieldText(String name,String num1,String num2,String num3,String birth,String mail,String qq,String workunit,String address) {
		nameField.setText(name);
		phoneField1.setText(num1);
		phoneField2.setText(num2);
		homeField.setText(num3);
		birthField.setText(birth);
		mailField.setText(mail);
		qqField.setText(qq);
		workunitField.setText(workunit);
		addressField.setText(address);		 		
	}	
}
