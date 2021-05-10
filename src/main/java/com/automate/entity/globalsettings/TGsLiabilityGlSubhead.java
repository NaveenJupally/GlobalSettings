package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_liability_gl_subhead database table.
 * 
 */
@Entity
@Table(name="t_gs_liability_gl_subhead")
@NamedQuery(name="TGsLiabilityGlSubhead.findAll", query="SELECT t FROM TGsLiabilityGlSubhead t")
public class TGsLiabilityGlSubhead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_liability_gl_subhead_id")
	private int gsLiabilityGlSubheadId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="liability_gl_head")
	private String liabilityGlHead;

	@Column(name="liability_gl_subhead_name")
	private String liabilityGlSubheadName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name = "parent_org_id")
	private int parentOrgId;

	//bi-directional many-to-one association to TGsOrganaization
	/*@ManyToOne
	@JoinColumn(name="parent_org_id")
	private TGsOrganaization TGsOrganaization;*/

	public TGsLiabilityGlSubhead() {
	}

	public int getGsLiabilityGlSubheadId() {
		return this.gsLiabilityGlSubheadId;
	}

	public void setGsLiabilityGlSubheadId(int gsLiabilityGlSubheadId) {
		this.gsLiabilityGlSubheadId = gsLiabilityGlSubheadId;
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

	public String getLiabilityGlHead() {
		return this.liabilityGlHead;
	}

	public void setLiabilityGlHead(String liabilityGlHead) {
		this.liabilityGlHead = liabilityGlHead;
	}

	public String getLiabilityGlSubheadName() {
		return this.liabilityGlSubheadName;
	}

	public void setLiabilityGlSubheadName(String liabilityGlSubheadName) {
		this.liabilityGlSubheadName = liabilityGlSubheadName;
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
	
	@Column(name = "status")
	private String status;

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