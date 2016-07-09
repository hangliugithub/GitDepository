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
		addbutton=new JButton("���");
		addbutton.setMnemonic('A');//�����ִ�Сд
		addbutton.setToolTipText("�ɰ�ALT+A�������");
		delbutton=new JButton("ɾ��");
		delbutton.setMnemonic('D');//�����ִ�Сд
		delbutton.setToolTipText("�ɰ�ALT+D����ɾ��");
		modifybutton=new JButton("�޸�");
		modifybutton.setMnemonic('M');//�����ִ�Сд
		modifybutton.setToolTipText("�ɰ�ALT+M�����޸�");
		
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
			   
				centerPanel2=new CenterPanel();      //��ʱΪ��һ��CenterPanel																	
				int option = JOptionPane.showConfirmDialog(null,centerPanel2,"�����ϵ��",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
				if(option == JOptionPane.OK_OPTION){
					
					String inputName = centerPanel2.getNameText();
					String inputPhone1 = centerPanel2.getPhonenum1Text();
					if(inputName.equals("")){
						JOptionPane.showMessageDialog(null,"��������������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
						return; //�費��Ҫreturn 
					}
					else if(inputPhone1.equals("")){
						JOptionPane.showMessageDialog(null,"����������绰���룱","��ʾ",JOptionPane.INFORMATION_MESSAGE);																	
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
						ConnectionData conObject=new ConnectionData();//��������
						result=conObject.UpdateData(sql);
						if(result!=0){
							 JOptionPane.showMessageDialog(null,"�û���Ϣ��ӳɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);						 				
							 NamePanel.refresh(); //ˢ�����					        	 
						 }
					    conObject.closeData();//�Ͽ�����  �����ں��棬˳���ܸı䣡��������	
					}						
		}
	}	 
	}
	
    static class Del implements ActionListener{
		public void actionPerformed(ActionEvent e){			
			if(!NamePanel.nameList.isSelectionEmpty()){
				
                String delName = NamePanel.getSelectedText();						
				String sql = "delete from phonetable where name ='"+delName+"'";
				int option = JOptionPane.showConfirmDialog(null,"���Ҫɾ��["+delName+"]��","��ʾ",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION){
					ConnectionData conObject=new ConnectionData();//��������
					int result=conObject.UpdateData(sql);
					 if(result!=0){
						 JOptionPane.showMessageDialog(null,"�û�["+delName+"]����Ϣ�ѳɹ�ɾ����","��ʾ",JOptionPane.INFORMATION_MESSAGE);						    
						 NamePanel.refresh(); //ˢ�����
					 }							   
					conObject.closeData();//�Ͽ�����	
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null,"������ѡ������ɾ����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			}			
		}		
	}	
     class  Modify implements ActionListener{
		public void actionPerformed(ActionEvent event){	
			
			if(NamePanel.nameList.isSelectionEmpty()){
				JOptionPane.showMessageDialog(null,"������ѡ�������޸ģ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
			else{
				
				if(event.getActionCommand().equals("�޸�")){
				MainFrame.centerPanel1.setFieldEditable(true,false);
				modifybutton.setText("�����޸�");			
				}
				else if(event.getActionCommand().equals("�����޸�")){
					
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
				
				ConnectionData conObject=new ConnectionData();//��������
				int result=0;
				result=conObject.UpdateData(sql);
				if(result!=0){
					 JOptionPane.showMessageDialog(null,"�û�["+selectName+"]����Ϣ���³ɹ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);						    				
					 NamePanel.refresh(); //ˢ�����//
				 }							    
				conObject.closeData();//�Ͽ�����	
				modifybutton.setText("�޸�");
				MainFrame.centerPanel1.setFieldEditable(false,false);
		    	}		
			}  	
		}
	}
}










