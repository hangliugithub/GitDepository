package mypackage;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SearchPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private ButtonGroup group;
	private JRadioButton nameButton,numButton;	
	private JTextField inputField;
	private JButton searchButton;
	private JList<String> nameList;
	private String name[];
	public SearchPanel(){
		setLayout(new FlowLayout());
		
		inputField = new JTextField("",15);
		inputField.setForeground(Color.red);
		inputField.setFont(new Font("Serif", Font.PLAIN, 16));
		inputField.setToolTipText("在此输入你要搜索的内容");
		inputField.addActionListener(new Search());
		
		nameButton =new JRadioButton("姓名搜索");
		nameButton.setSelected(true);
		nameButton.addActionListener(new ActionListener()
		 {
				public void actionPerformed(ActionEvent e){
					inputField.setDocument(new PlainDocument());
					inputField.setText("");											 
				}
			}
		);
		numButton = new JRadioButton("号码搜索");
		numButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					inputField.setDocument(new NumOnly());
					inputField.setText("");					
				}
			}
		);
		group = new ButtonGroup();
		group.add(nameButton);
		group.add(numButton);
		
		
		
		
		searchButton = new JButton("搜索");
		searchButton.setMnemonic('F');
		searchButton.setToolTipText("可按Alt+F进行搜索");
		searchButton.addActionListener(new Search());
		
		add(nameButton);
		add(numButton);
		add(inputField);
		add(searchButton);
					 
		this.setSize(100, 20);
		this.setVisible(true);
	}
	
	class Search implements ActionListener{
		private String inputContent;
		private ResultSet result;					
		public void actionPerformed(ActionEvent e){					
			name = NamePanel.getAllName();
			nameList=NamePanel.getNameList();
			nameList.setListData(name); 
			
			inputContent = inputField.getText();
			if(inputContent.equals("")){
				JOptionPane.showMessageDialog(null,"请先输入再进行搜索！","信息提示",JOptionPane.WARNING_MESSAGE);
			}
			else{	
				
				if(nameButton.isSelected()){
					int i = 0;
					for(i=0;i<name.length;i++){
						if(name[i].equals(inputContent)){
							nameList.setSelectedIndex(i);
							break;
						}
					}
			  System.out.println("i="+i);
			  
					if(i==name.length && !name[i-1].equals(inputContent)){
							
						 JOptionPane.showMessageDialog(null,"没有找到您要的信息！","信息提示",JOptionPane.INFORMATION_MESSAGE);						 
					}														  
				}
				else if(numButton.isSelected())					
				{
					ConnectionData conObject=new ConnectionData();
					int i = 0;
					String sql="select * from phonetable where phoneNumber1 = '"+inputContent+"' or phoneNumber2= '"+inputContent+"' ";
					try{
						
						result=conObject.QueryData(sql);
						if(result.next()){
							
							String sname = result.getString(1);
							 
							for(i=0;i<name.length;i++)
								if(name[i].equals(sname)){
									nameList.setSelectedIndex(i);
									break;
								}							
						}	
						else{
							 JOptionPane.showMessageDialog(null,"没有找到您要的信息！","信息提示",JOptionPane.INFORMATION_MESSAGE);	
						}
						}catch(Exception ee){ee.printStackTrace();}
				}
			}
		}
	}
}


