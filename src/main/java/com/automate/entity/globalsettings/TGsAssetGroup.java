package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_asset_group database table.
 * 
 */
@Entity
@Table(name="t_gs_asset_group")
@NamedQuery(name="TGsAssetGroup.findAll", query="SELECT t FROM TGsAssetGroup t")
public class TGsAssetGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_asset_group_id")
	private int gsAssetGroupId;

	@Column(name="asset_class_name")
	private String assetClassName;

	@Column(name="asset_nature")
	private String assetNature;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="Status")
	private String Status;
	
	@Column(name = "parent_org_id")
	private int parentOrgId;
		
	
	@Column(name="user_id")
	private int userId;
	
	//bi-directional many-to-one association to TGsOrganaization
	/*@ManyToOne
	@JoinColumn(name="parent_org_id")
	private TGsOrganaization TGsOrganaization;*/

	public TGsAssetGroup() {
	}

	public int getGsAssetGroupId() {
		return this.gsAssetGroupId;
	}

	public void setGsAssetGroupId(int gsAssetGroupId) {
		this.gsAssetGroupId = gsAssetGroupId;
	}

	 
	public int getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(int parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAssetClassName() {
		return this.assetClassName;
	}

	public void setAssetClassName(String assetClassName) {
		this.assetClassName = assetClassName;
	}

	public String getAssetNature() {
		return this.assetNature;
	}

	public void setAssetNature(String assetNature) {
		this.assetNature = assetNature;
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
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	/*public TGsOrganaization getTGsOrganaization() {
		return this.TGsOrganaization;
	}

	public void setTGsOrganaization(TGsOrganaization TGsOrganaization) {
		this.TGsOrganaization = TGsOrganaization;
	}*/

   

}