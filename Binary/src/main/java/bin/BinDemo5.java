package bin;

import org.apache.commons.lang.StringUtils;

/**
 * ��ѧ����>>
 * 	-��λ�������������0��������1�����������ѧ����
 * 	-����������С����ȡ��
 * 	-��>>>���߼����ƣ���λֻ����0
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
