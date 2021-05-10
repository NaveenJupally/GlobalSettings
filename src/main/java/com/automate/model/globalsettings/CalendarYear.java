package com.automate.model.globalsettings;

import java.util.List;

import com.automate.enums.Status;

public class CalendarYear {

	private int calendarYearId;
	private int parentOrgId;
	private String calendarYear;
	private String startDate;
	private String endDate;
	private List<YearsPeriod> yearsPeriods;
	private Status status;
	private int errorCode;

	public int getCalendarYearId() {
		return calendarYearId;
	}

	public void setCalendarYearId(int calendarYearId) {
		this.calendarYearId = calendarYearId;
	}

	public String getCalendarYear() {
		return calendarYear;
	}

	public void setCalendarYear(String calendarYear) {
		this.calendarYear = calendarYear;
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

	public int getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(int parentOrgId) {
		this.parentOrgId = parentOrgId;
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

	public List<YearsPeriod> getYearsPeriods() {
		return yearsPeriods;
	}

	public void setYearsPeriods(List<YearsPeriod> yearsPeriods) {
		this.yearsPeriods = yearsPeriods;
	}

}
