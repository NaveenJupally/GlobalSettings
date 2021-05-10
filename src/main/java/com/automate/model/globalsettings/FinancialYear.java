package com.automate.model.globalsettings;

import java.util.List;

import com.automate.enums.Status;

/**
 * @author sindh
 *
 */
public class FinancialYear {

	private int financialYearId;
	private int parentOrgId;
	private String financialYear;
	private String startDate;
	private String endDate;
	private List<YearsPeriod> fcyPeriod;
	private Status status;
	

	public List<YearsPeriod> getFcyPeriod() {
		return fcyPeriod;
	}

	public void setFcyPeriod(List<YearsPeriod> fcyPeriod) {
		this.fcyPeriod = fcyPeriod;
	}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	

}
