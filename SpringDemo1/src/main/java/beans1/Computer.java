package beans1;

import java.io.Serializable;
import java.util.Arrays;

public class Computer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String model;
	private String cpu;
	private String ram;
	private String disk;
	private String gpu;
	private int[] resolution;
	
	public Computer() {
		// TODO Auto-generated constructor stub
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public int[] getResolution() {
		return resolution;
	}

	public void setResolution(int[] resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "Computer [model=" + model + ", cpu=" + cpu + ", ram=" + ram + ", disk=" + disk + ", gpu=" + gpu
				+ ", resolution=" + Arrays.toString(resolution) + "]";
	}
	
	

}
