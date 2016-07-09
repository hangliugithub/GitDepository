package mypackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	JButton addbutton,delbutton;
	static JButton modifybutton;
	JList<?> nameList = null;
	String []name=null;
	public ButtonPanel(){
		addbutton=new JButton("添加");
		addbutton.setMnemonic('A');//不区分大小写
		addbutton.setToolTipText("可按ALT+A进行添加");
		delbutton=new JButton("删除");
		delbutton.setMnemonic('D');//不区分大小写
		delbutton.setToolTipText("可按ALT+D进行删除");
		modifybutton=new JButton("修改");
		modifybutton.setMnemonic('M');//不区分大小写
		modifybutton.setToolTipText("可按ALT+M进行修改");
		
		add(addbutton);
		add(delbutton);
		add(modifybutton);
		addbutton.addActionListener(new Add());	
		delbutton.addActionListener(new Del());
		modifybutton.addActionListener(new Modify());	
		}
    static class Add implements ActionListener{
		CenterPanel centerPanel2;
		public void actionPerformed(ActionEvent e){	
			   
				centerPanel2=new CenterPanel();      //此时为另一个CenterPanel																	
				int option = JOptionPane.showConfirmDialog(null,centerPanel2,"添加联系人",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
				if(option == JOptionPane.OK_OPTION){
					
					String inputName = centerPanel2.getNameText();
					String inputPhone1 = centerPanel2.getPhonenum1Text();
					if(inputName.equals("")){
						JOptionPane.showMessageDialog(null,"您必须输入姓名","提示",JOptionPane.INFORMATION_MESSAGE);
						return; //需不需要return 
					}
					else if(inputPhone1.equals("")){
						JOptionPane.showMessageDialog(null,"您必须输入电话号码１","提示",JOptionPane.INFORMATION_MESSAGE);																	
						return; 
					}
					else{	
						int result=0;
						String inputPhone2 = centerPanel2.getPhonenum2Text();
						String inputHomePhone = centerPanel2.getHomenumText();
						String inputBirth=centerPanel2.getBirthText();
						String mail = centerPanel2.getMailText();
						String qq = centerPanel2.getQQText();
						String workunit = centerPanel2.getWorkunitText();
						String address = centerPanel2.getAddressText();
						String sql = "insert into phonetable values('"+inputName+"','"+inputPhone1+"','"+inputPhone2+"','"+inputHomePhone+"','"+inputBirth+"','"+mail+"','"+qq+"','"+workunit+"','"+address+"')";
						ConnectionData conObject=new ConnectionData();//建立连接
						result=conObject.UpdateData(sql);
						if(result!=0){
							 JOptionPane.showMessageDialog(null,"用户信息添加成功！","提示",JOptionPane.INFORMATION_MESSAGE);						 				
							 NamePanel.refresh(); //刷新面板					        	 
						 }
					    conObject.closeData();//断开连接  必须在后面，顺序不能改变！！！！！	
					}						
		}
	}	 
	}
	
    static class Del implements ActionListener{
		public void actionPerformed(ActionEvent e){			
			if(!NamePanel.nameList.isSelectionEmpty()){
				
                String delName = NamePanel.getSelectedText();						
				String sql = "delete from phonetable where name ='"+delName+"'";
				int option = JOptionPane.showConfirmDialog(null,"真的要删除["+delName+"]吗？","提示",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION){
					ConnectionData conObject=new ConnectionData();//建立连接
					int result=conObject.UpdateData(sql);
					 if(result!=0){
						 JOptionPane.showMessageDialog(null,"用户["+delName+"]的信息已成功删除！","提示",JOptionPane.INFORMATION_MESSAGE);						    
						 NamePanel.refresh(); //刷新面板
					 }							   
					conObject.closeData();//断开连接	
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null,"必须先选定才能删除！","提示",JOptionPane.INFORMATION_MESSAGE);
			}			
		}		
	}	
     class  Modify implements ActionListener{
		public void actionPerformed(ActionEvent event){	
			
			if(NamePanel.nameList.isSelectionEmpty()){
				JOptionPane.showMessageDialog(null,"必须先选定才能修改！","提示",JOptionPane.INFORMATION_MESSAGE);
				}
			else{
				
				if(event.getActionCommand().equals("修改")){
				MainFrame.centerPanel1.setFieldEditable(true,false);
				modifybutton.setText("保存修改");			
				}
				else if(event.getActionCommand().equals("保存修改")){
					
				String selectName = NamePanel.getSelectedText();	
				String inputPhone1 = MainFrame.centerPanel1.getPhonenum1Text();
				String inputPhone2 = MainFrame.centerPanel1.getPhonenum2Text();
				String inputHomePhone = MainFrame.centerPanel1.getHomenumText();
				String inputBirth=MainFrame.centerPanel1.getBirthText();
				String inputMail = MainFrame.centerPanel1.getMailText();
				String inputQQ = MainFrame.centerPanel1.getQQText();
				String inputWorkunit = MainFrame.centerPanel1.getWorkunitText();
				String inputAddress = MainFrame.centerPanel1.getAddressText();
				
				String sql="update phonetable set phoneNumber1='"+inputPhone1+"',phoneNumber2='"+inputPhone2+"',homeNumber='"+inputHomePhone+"',birth='"+inputBirth+"',mail='"+inputMail+"',qq='"+inputQQ+"',workUnit='"+inputWorkunit+"',address='"+inputAddress+"'  where name='"+selectName+"' ";
				
				ConnectionData conObject=new ConnectionData();//建立连接
				int result=0;
				result=conObject.UpdateData(sql);
				if(result!=0){
					 JOptionPane.showMessageDialog(null,"用户["+selectName+"]的信息更新成功！","提示",JOptionPane.INFORMATION_MESSAGE);						    				
					 NamePanel.refresh(); //刷新面板//
				 }							    
				conObject.closeData();//断开连接	
				modifybutton.setText("修改");
				MainFrame.centerPanel1.setFieldEditable(false,false);
		    	}		
			}  	
		}
	}
}










