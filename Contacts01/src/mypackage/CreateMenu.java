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
		JMenu menu1=new JMenu("系统管理(S)");
		menu1.setMnemonic('S');
		menu1.addSeparator();	///////////////////////////////添加分隔符
	    menuitem5=new JMenuItem("退出系统");
	    menuitem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,java.awt.event.InputEvent.CTRL_MASK));
		menuitem5.addActionListener(this);
		menu1.add(menuitem5);
		menubar.add(menu1);
		
		JMenu menu2=new JMenu("电话本管理(W)");
		menu2.setMnemonic('W');
				
	    menuitem21=new JMenuItem("添加信息");
		menuitem22=new JMenuItem("修改信息");
		menuitem23=new JMenuItem("删除信息");
		menuitem24=new JMenuItem("统计联系人");
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
		
		JMenu menu3=new JMenu("关于(L)");
		menu3.setMnemonic('L');
		menuitem31=new JMenuItem("关于作者");
		menu3.add(menuitem31);
		menuitem31.addActionListener(this);
		menubar.add(menu3);	
		 
        
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menuitem5) {System.exit(0);}
		else if(e.getSource()==menuitem21){new ButtonPanel.Add().actionPerformed(e);}
		else if(e.getSource()==menuitem22){modify(e);}
		else if(e.getSource()==menuitem23){new ButtonPanel.Del().actionPerformed(e);}
		else if(e.getSource()==menuitem24){JOptionPane.showMessageDialog(null,"本通讯录共有"+NamePanel.length+"条记录!","统计",JOptionPane.INFORMATION_MESSAGE);}
		else if(e.getSource()==menuitem31){JOptionPane.showMessageDialog(null,"本软件作者：胡逢冰、侯文军、王财旺、叶翔、温博一","关于作者",JOptionPane.INFORMATION_MESSAGE);
	   }			
	}
	private void modify(ActionEvent event) {
		if(NamePanel.nameList.isSelectionEmpty()){
			JOptionPane.showMessageDialog(null,"必须先选定才能修改！","提示",JOptionPane.INFORMATION_MESSAGE);
			}
		else{		
			MainFrame.centerPanel1.setFieldEditable(true,false);
			ButtonPanel.modifybutton.setText("保存修改");			
			
		    if(event.getActionCommand().equals("保存修改")){	
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
				 NamePanel.refresh(); //刷新面板
			 }							    
			conObject.closeData();//断开连接	
			ButtonPanel.modifybutton.setText("修改");
			MainFrame.centerPanel1.setFieldEditable(false,false);
	    	}	
		}
		
	}
	
}
