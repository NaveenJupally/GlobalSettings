package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the calendar_year_mapping database table.
 * 
 */
@Entity
@Table(name = "financial_year_mapping")
public class FinancialYearMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "calendar_period")
	private String calendarPeriod;

	private String month;

	@Column(name = "posting_period")
	private int postingPeriod;

	// bi-directional many-to-one association to TGsCalendarYear
	@ManyToOne
	@JoinColumn(name = "gs_financial_year_id")
	private TGsFinancialYear TGsFinancialYear;

	public FinancialYearMapping() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalendarPeriod() {
		return this.calendarPeriod;
	}

	public void setCalendarPeriod(String calendarPeriod) {
		this.calendarPeriod = calendarPeriod;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getPostingPeriod() {
		return this.postingPeriod;
	}

	public void setPostingPeriod(int postingPeriod) {
		this.postingPeriod = postingPeriod;
	}

	public TGsFinancialYear getTGsFinancialYear() {
		return TGsFinancialYear;
	}

	public void setTGsFinancialYear(TGsFinancialYear tGsFinancialYear) {
		TGsFinancialYear = tGsFinancialYear;
	}

}