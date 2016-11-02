package com.cloud.model;

import java.math.BigDecimal;
import java.util.List;


public class CloudModel {

	UserObject user;
	boolean isAuthenticated;
	Diagnostic diagnostic;
	String authenticationToken;
	List<Server> listOfServer;
	BigDecimal totalMemoryUsage;
	BigDecimal totalVcpuUsage;
	BigDecimal totalLocalGbUsage;

	

	

	public BigDecimal getTotalMemoryUsage() {
		return totalMemoryUsage;
	}


	public void setTotalMemoryUsage(BigDecimal totalMemoryUsage) {
		this.totalMemoryUsage = totalMemoryUsage;
	}


	public BigDecimal getTotalVcpuUsage() {
		return totalVcpuUsage;
	}


	public void setTotalVcpuUsage(BigDecimal totalVcpuUsage) {
		this.totalVcpuUsage = totalVcpuUsage;
	}


	public BigDecimal getTotalLocalGbUsage() {
		return totalLocalGbUsage;
	}


	public void setTotalLocalGbUsage(BigDecimal totalLocalGbUsage) {
		this.totalLocalGbUsage = totalLocalGbUsage;
	}


	public List<Server> getListOfServer() {
		return listOfServer;
	}


	public void setListOfServer(List<Server> listOfServer) {
		this.listOfServer = listOfServer;
	}


	public String getAuthenticationToken() {
		return authenticationToken;
	}

	
		public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public Diagnostic getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(Diagnostic diagnostic) {
		this.diagnostic = diagnostic;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	

	public UserObject getUser() {
		return user;
	}

	public void setUser(UserObject user) {
		this.user = user;
	}
	
}
