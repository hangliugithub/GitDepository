package bin;

import org.apache.commons.lang.StringUtils;

public class BinDemo6 {
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			System.out.println((i%8)+","+(i&0x7));
			System.out.println(StringUtils.leftPad(Integer.toBinaryString(i),32,'0'));
			System.out.println(StringUtils.leftPad(Integer.toBinaryString(0x7),32,'0'));
			System.out.println(StringUtils.leftPad(Integer.toBinaryString(i&0x7),32,'0'));
			
		}
	}
}
