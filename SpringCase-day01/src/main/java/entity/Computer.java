package entity;

import java.io.Serializable;

public class Computer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mainboard;
	private String cpu;
	private String ram;
	private String disk;
	
	public Computer() {
		System.out.println("ÊµÀý»¯Computer...");
	}

	
	public Computer(String mainboard, String cpu, String ram, String disk) {
		super();
		this.mainboard = mainboard;
		this.cpu = cpu;
		this.ram = ram;
		this.disk = disk;
	}



	public String getMainboard() {
		return mainboard;
	}

	public void setMainboard(String mainboard) {
		this.mainboard = mainboard;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	@Override
	public String toString() {
		return "Computer [mainboard=" + mainboard + ", cpu=" + cpu + ", ram=" + ram + ", disk=" + disk + "]";
	}
	
	
	
	
}
