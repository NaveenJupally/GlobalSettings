package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_asset_nature database table.
 * 
 */
@Entity
@Table(name="t_gs_asset_nature")
@NamedQuery(name="TGsAssetNature.findAll", query="SELECT t FROM TGsAssetNature t")
public class TGsAssetNature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_asset_nature_id")
	private int gsAssetNatureId;

	@Column(name="asset_group")
	private String assetGroup;

	@Column(name="asset_nature_name")
	private String assetNatureName;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="org_name")
	private String orgName;

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
//	@ManyToOne
//	@JoinColumn(name="parent_org_id")
//	private TGsOrganaization TGsOrganaization;

	public TGsAssetNature() {
	}

	public int getGsAssetNatureId() {
		return this.gsAssetNatureId;
	}

	public void setGsAssetNatureId(int gsAssetNatureId) {
		this.gsAssetNatureId = gsAssetNatureId;
	}

	public String getAssetGroup() {
		return this.assetGroup;
	}

	public void setAssetGroup(String assetGroup) {
		this.assetGroup = assetGroup;
	}

	public String getAssetNatureName() {
		return this.assetNatureName;
	}

	public void setAssetNatureName(String assetNatureName) {
		this.assetNatureName = assetNatureName;
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
	
	
//
//	public TGsOrganaization getTGsOrganaization() {
//		return this.TGsOrganaization;
//	}
//
//	public void setTGsOrganaization(TGsOrganaization TGsOrganaization) {
//		this.TGsOrganaization = TGsOrganaization;
//	}

}