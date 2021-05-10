package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_liability_gl_head database table.
 * 
 */
@Entity
@Table(name="t_gs_liability_gl_head")
@NamedQuery(name="TGsLiabilityGlHead.findAll", query="SELECT t FROM TGsLiabilityGlHead t")
public class TGsLiabilityGlHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_liability_gl_head_id")
	private int gsLiabilityGlHeadId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="liability_gl_head_name")
	private String liabilityGlHeadName;

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
	/*@ManyToOne
	@JoinColumn(name="parent_org_id")
	private TGsOrganaization TGsOrganaization;*/

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TGsLiabilityGlHead() {
	}

	public int getGsLiabilityGlHeadId() {
		return this.gsLiabilityGlHeadId;
	}

	public void setGsLiabilityGlHeadId(int gsLiabilityGlHeadId) {
		this.gsLiabilityGlHeadId = gsLiabilityGlHeadId;
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

	public String getLiabilityGlHeadName() {
		return this.liabilityGlHeadName;
	}

	public void setLiabilityGlHeadName(String liabilityGlHeadName) {
		this.liabilityGlHeadName = liabilityGlHeadName;
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
	
	
	
	
	

	/*public TGsOrganaization getTGsOrganaization() {
		return this.TGsOrganaization;
	}

	public void setTGsOrganaization(TGsOrganaization TGsOrganaization) {
		this.TGsOrganaization = TGsOrganaization;
	}*/

}