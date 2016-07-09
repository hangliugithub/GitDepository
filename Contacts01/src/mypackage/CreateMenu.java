package mypackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
public class CreateMenu implements ActionListener{
	JMenuBar menubar=new JMenuBar();
	JMenuItem menuitem5,menuitem21,menuitem22,menuitem23,menuitem24,menuitem31; 	    
	public CreateMenu() {
		JMenu menu1=new JMenu("ϵͳ����(S)");
		menu1.setMnemonic('S');
		menu1.addSeparator();	///////////////////////////////��ӷָ���
	    menuitem5=new JMenuItem("�˳�ϵͳ");
	    menuitem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,java.awt.event.InputEvent.CTRL_MASK));
		menuitem5.addActionListener(this);
		menu1.add(menuitem5);
		menubar.add(menu1);
		
		JMenu menu2=new JMenu("�绰������(W)");
		menu2.setMnemonic('W');
				
	    menuitem21=new JMenuItem("�����Ϣ");
		menuitem22=new JMenuItem("�޸���Ϣ");
		menuitem23=new JMenuItem("ɾ����Ϣ");
		menuitem24=new JMenuItem("ͳ����ϵ��");
		menuitem21.addActionListener(this);
		menuitem22.addActionListener(this);
		menuitem23.addActionListener(this);
		menuitem24.addActionListener(this);	
		
		menuitem21.setEnabled(true);
		menuitem22.setEnabled(true);
		menuitem23.setEnabled(true);
		menuitem24.setEnabled(true);
		//menuitem4.setEnabled(true);
		
		menu2.add(menuitem21);
		menu2.add(menuitem22);
		menu2.add(menuitem23);
		menu2.add(menuitem24);
		menubar.add(menu2);
		
		JMenu menu3=new JMenu("����(L)");
		menu3.setMnemonic('L');
		menuitem31=new JMenuItem("��������");
		menu3.add(menuitem31);
		menuitem31.addActionListener(this);
		menubar.add(menu3);	
		 
        
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menuitem5) {System.exit(0);}
		else if(e.getSource()==menuitem21){new ButtonPanel.Add().actionPerformed(e);}
		else if(e.getSource()==menuitem22){modify(e);}
		else if(e.getSource()==menuitem23){new ButtonPanel.Del().actionPerformed(e);}
		else if(e.getSource()==menuitem24){JOptionPane.showMessageDialog(null,"��ͨѶ¼����"+NamePanel.length+"����¼!","ͳ��",JOptionPane.INFORMATION_MESSAGE);}
		else if(e.getSource()==menuitem31){JOptionPane.showMessageDialog(null,"��������ߣ�����������ľ�����������Ҷ�衢�²�һ","��������",JOptionPane.INFORMATION_MESSAGE);
	   }			
	}
	private void modify(ActionEvent event) {
		if(NamePanel.nameList.isSelectionEmpty()){
			JOptionPane.showMessageDialog(null,"������ѡ�������޸ģ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			}
		else{		
			MainFrame.centerPanel1.setFieldEditable(true,false);
			ButtonPanel.modifybutton.setText("�����޸�");			
			
		    if(event.getActionCommand().equals("�����޸�")){	
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
				 NamePanel.refresh(); //ˢ�����
			 }							    
			conObject.closeData();//�Ͽ�����	
			ButtonPanel.modifybutton.setText("�޸�");
			MainFrame.centerPanel1.setFieldEditable(false,false);
	    	}	
		}
		
	}
	
}
