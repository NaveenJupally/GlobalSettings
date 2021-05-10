package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_gl_account database table.
 * 
 */
@Entity
@Table(name="t_gs_gl_account")
@NamedQuery(name="TGsGlAccount.findAll", query="SELECT t FROM TGsGlAccount t")
public class TGsGlAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_gl_account_id")
	private int gsGlAccountId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="gl_account_name")
	private String glAccountName;

	@Column(name="gl_group_name")
	private String glGroupName;

	@Column(name="gl_sub_group_name")
	private String glSubGroupName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="vendor_visibility")
	private String vendorVisibility;
	
	@Column(name="status")
	private String status;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name = "parent_org_id")
	private int parentOrgId;

	//bi-directional many-to-one association to TGsOrganaization
	/*@ManyToOne
	@JoinColumn(name="parent_org_id")
	private TGsOrganaization TGsOrganaization;*/

	public TGsGlAccount() {
	}

	public int getGsGlAccountId() {
		return this.gsGlAccountId;
	}

	public void setGsGlAccountId(int gsGlAccountId) {
		this.gsGlAccountId = gsGlAccountId;
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

	public String getGlAccountName() {
		return this.glAccountName;
	}

	public void setGlAccountName(String glAccountName) {
		this.glAccountName = glAccountName;
	}

	public String getGlGroupName() {
		return this.glGroupName;
	}

	public void setGlGroupName(String glGroupName) {
		this.glGroupName = glGroupName;
	}

	public String getGlSubGroupName() {
		return this.glSubGroupName;
	}

	public void setGlSubGroupName(String glSubGroupName) {
		this.glSubGroupName = glSubGroupName;
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

	public String getVendorVisibility() {
		return this.vendorVisibility;
	}

	public void setVendorVisibility(String vendorVisibility) {
		this.vendorVisibility = vendorVisibility;
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