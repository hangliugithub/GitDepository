package priv.jesse.netctoss.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
/**
 * jdbc֧�ֵ�ʱ����java.sql���µ�ʱ��
 * java.sql.Date(������)
 * java.sql.Time(ʱ����)
 * java.sql.Timestamp(������ʱ����)
 * ���̳���java.util.Date
 * @author Jesse
 *
 */
public class Cost implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer costId;
	private String name;
	//����ʱ��
	private Integer baseDuration;
	//��������
	private Double baseCost;
	//��λ����
	private Double unitCost;
	//״̬ 0-��ͨ 1-��ͣ
	private String status;
	//����
	private String descr;
	//����ʱ��
	private Timestamp creatime;
	//��ͨʱ��
	private Timestamp startime;
	//�ʷ�����:����-1  �ײ�-2  ��ʱ-3
	private String costType;
	@Override
	public String toString() {
		return "Cost [costId=" + costId + ", name=" + name + ", baseDuration=" + baseDuration + ", baseCost=" + baseCost
				+ ", unitCost=" + unitCost + ", status=" + status + ", desct=" + descr + ", creatime=" + creatime
				+ ", startime=" + startime + ", costType=" + costType + "]";
	}
	public Integer getCostId() {
		return costId;
	}
	public void setCostId(Integer costId) {
		this.costId = costId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBaseDuration() {
		return baseDuration;
	}
	public void setBaseDuration(Integer baseDuration) {
		this.baseDuration = baseDuration;
	}
	public Double getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String desct) {
		this.descr = desct;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	public Timestamp getStartime() {
		return startime;
	}
	public void setStartime(Timestamp startime) {
		this.startime = startime;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	
	
}
