package mypackage;
import javax.swing.text.AttributeSet;   
import javax.swing.text.BadLocationException;   
import javax.swing.text.PlainDocument; 
public class NumOnly extends PlainDocument{		
	private static final long serialVersionUID = 1L;

	public void insertString(int offs,String str,AttributeSet a) throws BadLocationException{   
		for(int i=0;i<str.length();i++){  
			if(str.charAt(i)<'0'||str.charAt(i)>'9'){   
				return;   
			} 
		}   
		super.insertString(offs,str,a);  		
	}
}  