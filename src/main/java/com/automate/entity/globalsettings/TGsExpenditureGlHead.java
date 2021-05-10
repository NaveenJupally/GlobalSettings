package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_expenditure_gl_head database table.
 * 
 */
@Entity
@Table(name="t_gs_expenditure_gl_head")
@NamedQuery(name="TGsExpenditureGlHead.findAll", query="SELECT t FROM TGsExpenditureGlHead t")
public class TGsExpenditureGlHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_expenditure_gl_head_id")
	private int gsExpenditureGlHeadId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="expenditure_gl_head_name")
	private String expenditureGlHeadName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name = "parent_org_id")
	private int parentOrgId;
	
	@Column(name = "status")
	private String status;

	//bi-directional many-to-one association to TGsOrganaization
	//@ManyToOne
	//@JoinColumn(name="parent_org_id")
	//private TGsOrganaization TGsOrganaization;

	public TGsExpenditureGlHead() {
	}

	public int getGsExpenditureGlHeadId() {
		return this.gsExpenditureGlHeadId;
	}

	public void setGsExpenditureGlHeadId(int gsExpenditureGlHeadId) {
		this.gsExpenditureGlHeadId = gsExpenditureGlHeadId;
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

	public String getExpenditureGlHeadName() {
		return this.expenditureGlHeadName;
	}

	public void setExpenditureGlHeadName(String expenditureGlHeadName) {
		this.expenditureGlHeadName = expenditureGlHeadName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public TGsOrganaization getTGsOrganaization() {
		return this.TGsOrganaization;
	}

	public void setTGsOrganaization(TGsOrganaization TGsOrganaization) {
		this.TGsOrganaization = TGsOrganaization;
	}*/

}