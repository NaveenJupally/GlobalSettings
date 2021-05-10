package com.automate.model.globalsettings;

import com.automate.enums.Status;

public class GetCalendarYear {

	private int calendarYearId;
	private String calendarYear;
	private String startDate;
	private String endDate;
	private String month;
	private String postingPeriod;
	private String calendarPeriod;
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
