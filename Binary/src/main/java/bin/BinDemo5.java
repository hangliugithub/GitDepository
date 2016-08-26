package bin;

import org.apache.commons.lang.StringUtils;

/**
 * 数学右移>>
 * 	-高位补充规则：正数补0，负数补1，结果满足数学规则
 * 	-如果溢出，向小方向取整
 * 	-而>>>是逻辑右移：高位只补充0
 *
 * 	
 * @author Jesse
 *
 */
public class BinDemo5 {
	public static void main(String[] args) {
		int n = 80;//0x50
		int m = n>>1;
		System.out.println(n+":"+StringUtils.leftPad(Integer.toBinaryString(n), 32,'0'));
		System.out.println(m+":"+StringUtils.leftPad(Integer.toBinaryString(m), 32,'0'));
		System.out.println("-----------------------------------");
		
	}
}
