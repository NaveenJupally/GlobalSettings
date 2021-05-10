package com.automate.entity.globalsettings;

import java.io.Serializable;
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
 * The persistent class for the t_gs_financial_year database table.
 * 
 */
@Entity
@Table(name = "t_gs_financial_year")
@NamedQuery(name = "TGsFinancialYear.findAll", query = "SELECT t FROM TGsFinancialYear t")
public class TGsFinancialYear implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "gs_financial_year_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gsFinancialYearId;
	
	@Column(name = "user_id")
	private int userId;

	@Column(name = "parent_org_id")
	private int parentOrgId;


	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "financial_year")
	private String financialYear;

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

	public void setStatus(String status) {
		this.status = status;
	}

	// bi-directional many-to-one association to FinancialYearMapping
	@OneToMany(mappedBy = "TGsFinancialYear", cascade = CascadeType.ALL)
	private List<FinancialYearMapping> financialYearMappings;

	
	public List<FinancialYearMapping> getFinancialYearMappings() {
		return financialYearMappings;
	}

	public void setFinancialYearMappings(List<FinancialYearMapping> financialYearMappings) {
		this.financialYearMappings = financialYearMappings;
	}

	
	public TGsFinancialYear() {
	}

	public int getGsFinancialYearId() {
		return this.gsFinancialYearId;
	}

	public void setGsFinancialYearId(int gsFinancialYearId) {
		this.gsFinancialYearId = gsFinancialYearId;
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

	public String getFinancialYear() {
		return this.financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
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

}