package com.automate.model.globalsettings;

import com.automate.enums.Status;

public class Rule {

	private int ruleId;
	private String rule1;
	private String rule2;
	private Status status;
	private int errorCode;

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getRule1() {
		return rule1;
	}

	public void setRule1(String rule1) {
		this.rule1 = rule1;
	}

	public String getRule2() {
		return rule2;
	}

	public void setRule2(String rule2) {
		this.rule2 = rule2;
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
