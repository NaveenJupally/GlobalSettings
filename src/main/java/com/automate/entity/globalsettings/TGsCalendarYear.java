package com.automate.entity.globalsettings;

import java.io.Serializable;
//import java.sql.Date;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the t_gs_calendar_year database table.
 * 
 */
@Entity
@Table(name = "t_gs_calendar_year")
@NamedQuery(name = "TGsCalendarYear.findAll", query = "SELECT t FROM TGsCalendarYear t")
public class TGsCalendarYear implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gs_calendar_year_id")
	private int gsCalendarYearId;

	
	@Column(name = "user_id")
	private int userId;

	@Column(name = "parent_org_id")
	private int parentOrgId;

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(int parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	@Column(name = "calendar_year")
	private String calendarYear;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// bi-directional many-to-one association to CalendarYearMapping
	@OneToMany(mappedBy = "TGsCalendarYear", cascade = CascadeType.ALL)
	private List<CalendarYearMapping> calendarYearMappings;

	public TGsCalendarYear() {
	}

	public int getGsCalendarYearId() {
		return this.gsCalendarYearId;
	}

	public void setGsCalendarYearId(int gsCalendarYearId) {
		this.gsCalendarYearId = gsCalendarYearId;
	}

	public String getCalendarYear() {
		return this.calendarYear;
	}

	public void setCalendarYear(String calendarYear) {
		this.calendarYear = calendarYear;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<CalendarYearMapping> getCalendarYearMappings() {
		return this.calendarYearMappings;
	}

	public void setCalendarYearMappings(List<CalendarYearMapping> calendarYearMappings) {
		this.calendarYearMappings = calendarYearMappings;
	}

	public CalendarYearMapping addCalendarYearMapping(CalendarYearMapping calendarYearMapping) {
		getCalendarYearMappings().add(calendarYearMapping);
		calendarYearMapping.setTGsCalendarYear(this);

		return calendarYearMapping;
	}

	public CalendarYearMapping removeCalendarYearMapping(CalendarYearMapping calendarYearMapping) {
		getCalendarYearMappings().remove(calendarYearMapping);
		calendarYearMapping.setTGsCalendarYear(null);

		return calendarYearMapping;
	}

	

}