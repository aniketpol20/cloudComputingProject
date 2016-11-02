package com.cloud.model;

import java.util.HashMap;

public class Diagnostic {

	HashMap<String, Integer> ramUsage;
	HashMap<String, Integer> cpuUsage;
	HashMap<String, Integer> diskUsage;
	public HashMap<String, Integer> getRamUsage() {
		return ramUsage;
	}
	public void setRamUsage(HashMap<String, Integer> ramUsage) {
		this.ramUsage = ramUsage;
	}
	public HashMap<String, Integer> getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(HashMap<String, Integer> cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public HashMap<String, Integer> getDiskUsage() {
		return diskUsage;
	}
	public void setDiskUsage(HashMap<String, Integer> diskUsage) {
		this.diskUsage = diskUsage;
	}
	
	
}
