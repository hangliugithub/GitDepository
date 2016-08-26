package bin;

import org.apache.commons.lang.StringUtils;
/**
 * Âß¼­ÓÒÒÆ¶¯>>>
 * Âß¼­×óÒÆ<<
 * @author Jesse
 *
 */
public class BinDemo4 {
	public static void main(String[] args) {
		int n = 0x14d751da;
		int m = n>>>1;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(n), 32,'0'));
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(m), 32,'0'));
		m = n>>>8;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(m), 32,'0'));
		int k = (n>>>8)&0xff;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(k), 32,'0'));
		
		System.out.println("----------------------");
		
		int[] res = splitToBin(n);
		for(int s:res){
			System.out.println(StringUtils.leftPad(Integer.toBinaryString(s), 32,'0'));
		}
		
		System.out.println("----------------------");
		int d1 = res[0];
		int d2 = res[1];
		int d3 = res[2];
		int d4 = res[3];
		int t = (d1<<24)+(d2<<16)+(d3<<8)+(d4<<0);
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(t), 32,'0'));
		
	}

	private static int[] splitToBin(int n) {
		int[] r = new int[4];
		r[0]=(n>>>24)&0xff;
		r[1]=(n>>>16)&0xff;
		r[2]=(n>>>8)&0xff;
		r[3]=(n>>>0)&0xff;
		return r;
	}
}
	
	
	 