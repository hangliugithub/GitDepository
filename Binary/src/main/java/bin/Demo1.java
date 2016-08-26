package bin;

import org.apache.commons.lang.StringUtils;

public class Demo1 {
	public static void main(String[] args) {
		int i = 8;
		System.out.println(Integer.toBinaryString(i));
		// System.out.println(Integer.toBinaryString(12));
		for (int j = 0; j < 16; j++) {
			System.out.println(j + ":\t" + Integer.toBinaryString(j));
		}

		int max = Integer.MAX_VALUE;
		System.out.println(max);
		System.out.println(Integer.toBinaryString(max));

		int min = Integer.MIN_VALUE;
		System.out.println(min);
		System.out.println(Integer.toBinaryString(min));

		long lMax = Long.MAX_VALUE;
		System.out.println(lMax);
		System.out.println(Long.toBinaryString(lMax));

		long lMin = Long.MIN_VALUE;
		System.out.println(lMin);
		System.out.println(Long.toBinaryString(lMin));

		// -1的规律
		int n = -1;
		System.out.println(Integer.toBinaryString(n));
		long l = -1l;
		System.out.println(Long.toBinaryString(l));

		// -2的规律
		int m = -2;
		System.out.println(Integer.toBinaryString(m));
		long x = -2l;
		System.out.println(Long.toBinaryString(x));
		
		System.out.println("-----------------------------");
		
		for(int k=-10;k<10;k++){
			String b = Integer.toBinaryString(k);
			//补齐32位
			System.out.println(StringUtils.leftPad(b, 32,'0'));
		}
		
		System.out.println("------------------------------");
		System.out.println(Integer.MAX_VALUE+1);
		System.out.println(Integer.MIN_VALUE-1);
		
		n = 345;
		//溢出一圈-1
		m=n+Integer.MAX_VALUE+1+Integer.MAX_VALUE;
		System.out.println(m);//344
		//溢出一圈
		m = n+Integer.MAX_VALUE+1+Integer.MAX_VALUE+1;
		System.out.println(m);//345
		
	}
}










