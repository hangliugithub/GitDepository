package bin;

import org.apache.commons.lang.StringUtils;

public class BinDemo3 {
	public static void main(String[] args){
		int n = 0B00010100110101110101000111101010;
		//int n = 0x14d751ea;//16进制（缩写2进制）
		int m = 0xff;
		int k = n&m;//取n的低八位
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n),32,'0'));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(m),32,'0'));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k),32,'0'));
	}
}
