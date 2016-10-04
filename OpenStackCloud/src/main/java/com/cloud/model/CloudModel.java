package com.cloud.model;

import java.util.List;

public class CloudModel {

	UserObject user;
	List<String> serverIds;
	boolean isAuthenticated;
	Diagnostic diagnostic;
	//List<E>
	

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

	public List<String> getServerIds() {
		return serverIds;
	}

	public void setServerIds(List<String> serverIds) {
		this.serverIds = serverIds;
	}

	public UserObject getUser() {
		return user;
	}

	public void setUser(UserObject user) {
		this.user = user;
	}
	
}
