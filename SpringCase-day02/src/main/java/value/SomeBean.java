package value;

import java.io.Serializable;
/**
 * ��ʾʹ��spring���ʽ��ȡ����bean������ֵ
 * @author Jesse
 *
 */
public class SomeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ename;
	private String interest;
	private double score;
	private String pageSize;
	
	public SomeBean() {
		// TODO Auto-generated constructor stub
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "SomeBean [ename=" + ename + ", interest=" + interest + ", score=" + score + ", pageSize=" + pageSize
				+ "]";
	}
	
	
	

}
