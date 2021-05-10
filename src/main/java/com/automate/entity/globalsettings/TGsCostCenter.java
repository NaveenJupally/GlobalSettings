package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_cost_center database table.
 * 
 */
@Entity
@Table(name="t_gs_cost_center")
@NamedQuery(name="TGsCostCenter.findAll", query="SELECT t FROM TGsCostCenter t")
public class TGsCostCenter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_cost_center_id")
	private int gsCostCenterId;

	@Column(name="cost_center_name")
	private String costCenterName;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name = "parent_org_id")
	private int parentOrgId;

	//bi-directional many-to-one association to TGsOrganaization
	//@ManyToOne
	//@JoinColumn(name="parent_org_id")
	//private TGsOrganaization TGsOrganaization;

	public TGsCostCenter() {
	}

	public int getGsCostCenterId() {
		return this.gsCostCenterId;
	}

	public void setGsCostCenterId(int gsCostCenterId) {
		this.gsCostCenterId = gsCostCenterId;
	}

	public String getCostCenterName() {
		return this.costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
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