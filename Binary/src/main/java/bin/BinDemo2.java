package bin;

import org.apache.commons.lang.StringUtils;

public class BinDemo2 {
	public static void main(String[] args) {
		int n = 55;
		System.out.println(n+":\t"+StringUtils.leftPad(Integer.toBinaryString(n), 32,'0'));
		int m = ~n;
		System.out.println(m+":\t"+StringUtils.leftPad(Integer.toBinaryString(m), 32, '0'));
		int k = m+1;
		System.out.println(k+":\t"+StringUtils.leftPad(Integer.toBinaryString(k), 32, '0'));
	}
}




