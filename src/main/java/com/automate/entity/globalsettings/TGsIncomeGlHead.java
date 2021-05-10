package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_income_gl_head database table.
 * 
 */
@Entity
@Table(name="t_gs_income_gl_head")
@NamedQuery(name="TGsIncomeGlHead.findAll", query="SELECT t FROM TGsIncomeGlHead t")
public class TGsIncomeGlHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_income_gl_head_id")
	private int gsIncomeGlHeadId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="income_gl_head_name")
	private String incomeGlHeadName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="status")
	private String status;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name = "parent_org_id")
	private int parentOrgId;

	//bi-directional many-to-one association to TGsOrganaization
	//@ManyToOne
	//@JoinColumn(name="parent_org_id")
	//private TGsOrganaization TGsOrganaization;

	public TGsIncomeGlHead() {
	}

	public int getGsIncomeGlHeadId() {
		return this.gsIncomeGlHeadId;
	}

	public void setGsIncomeGlHeadId(int gsIncomeGlHeadId) {
		this.gsIncomeGlHeadId = gsIncomeGlHeadId;
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

	public String getIncomeGlHeadName() {
		return this.incomeGlHeadName;
	}

	public void setIncomeGlHeadName(String incomeGlHeadName) {
		this.incomeGlHeadName = incomeGlHeadName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setParentOrgId(int parentOrgId2) {
		// TODO Auto-generated method stub
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	

/*	public TGsOrganaization getTGsOrganaization() {
		return this.TGsOrganaization;
	}

	public void setTGsOrganaization(TGsOrganaization TGsOrganaization) {
		this.TGsOrganaization = TGsOrganaization;
	}*/

	
	}

