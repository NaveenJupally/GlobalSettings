package com.automate.model.globalsettings;

import com.automate.enums.Status;

/**
 * @author sindh
 *
 */
public class GetFinancialYear {

	private int financialYearId;
	private int parentOrgId;
	private String financialYear;
	private String startDate;
	private String endDate;
	private String month;
	private String postingPeriod;
	private String calendarPeriod;
	private Status status;
	private int errorCode;
	
	public int getFinancialYearId() {
		return financialYearId;
	}
	public int getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(int parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public void setFinancialYearId(int financialYearId) {
		this.financialYearId = financialYearId;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPostingPeriod() {
		return postingPeriod;
	}
	public void setPostingPeriod(String postingPeriod) {
		this.postingPeriod = postingPeriod;
	}
	public String getCalendarPeriod() {
		return calendarPeriod;
	}
	public void setCalendarPeriod(String calendarPeriod) {
		this.calendarPeriod = calendarPeriod;
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
