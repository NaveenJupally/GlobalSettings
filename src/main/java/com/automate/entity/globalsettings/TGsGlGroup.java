package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_gl_group database table.
 * 
 */
@Entity
@Table(name="t_gs_gl_group")
@NamedQuery(name="TGsGlGroup.findAll", query="SELECT t FROM TGsGlGroup t")
public class TGsGlGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_gl_group_id")
	private int gsGlGroupId;

	@Column(name="account_type")
	private String accountType;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="gl_group_name")
	private String glGroupName;

	@Column(name="gl_head")
	private String glHead;

	@Column(name="org_name")
	private String orgName;

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
	/*@ManyToOne
	@JoinColumn(name="parent_org_id")
	private TGsOrganaization TGsOrganaization; */

	public TGsGlGroup() {
	}

	public int getGsGlGroupId() {
		return this.gsGlGroupId;
	}

	public void setGsGlGroupId(int gsGlGroupId) {
		this.gsGlGroupId = gsGlGroupId;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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

	public String getGlGroupName() {
		return this.glGroupName;
	}

	public void setGlGroupName(String glGroupName) {
		this.glGroupName = glGroupName;
	}

	public String getGlHead() {
		return this.glHead;
	}

	public void setGlHead(String glHead) {
		this.glHead = glHead;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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