package com.automate.model.globalsettings;

import java.util.List;

import com.automate.enums.Status;

/**
 * @author sindh
 *
 */
public class FYandCYConfiguration {

	private int parentOrgId;
	private List<Module> modules;
	private Status status;
	private int errorCode;

	

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public int getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(int parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
