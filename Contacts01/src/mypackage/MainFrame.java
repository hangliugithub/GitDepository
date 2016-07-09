package mypackage;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

//import cn.jesse.UiUtil;
 
public class MainFrame extends JFrame{
    private static final long serialVersionUID = 1L;    
    Container container; 
    static CenterPanel centerPanel1=new CenterPanel(); 
    //static CenterPanel centerPanel2=new CenterPanel();
  
	public MainFrame(){
		super("My PhoneBook");		 
		this.setJMenuBar(new CreateMenu().menubar);	
		container=this.getContentPane();	
		container.add(new SearchPanel(),BorderLayout.NORTH);		
		container.add(centerPanel1,BorderLayout.CENTER);
		centerPanel1.setFieldEditable(false,false);//…Ë÷√ «∑Òƒ‹±‡º≠
		container.add(new NamePanel(),BorderLayout.WEST);
		container.add(new ButtonPanel(),BorderLayout.SOUTH);
		
		this.setSize(433, 588);
		UiUtil.setFrameCenter(this);
		UiUtil.setFrameImage(this);
		this.setResizable(false);
		this.setVisible(true);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}













