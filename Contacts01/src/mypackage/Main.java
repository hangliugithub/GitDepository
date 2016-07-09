package mypackage;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class Main extends JFrame{
	private static final long serialVersionUID = 1L;	
	//static BeginFrame begin=new BeginFrame();
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(MyLookAndFeel.SYS_NIMBUS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrame frameobj=new MainFrame();	 //创建MainFrame对象	
		 //frameobj.setJMenuBar(menuobj.menubar);
		//frameobj.setVisible(true);
		frameobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
