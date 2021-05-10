package com.automate.entity.globalsettings;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the t_gs_profit_center database table.
 * 
 */
@Entity
@Table(name="t_gs_profit_center")
//
//@NamedQuery(name="TGsProfitCenter.findAll", query="SELECT t FROM TGsProfitCenter t")
public class TGsProfitCenter {
	@Id
	@Column(name="gs_profit_center_id")
	private int gsProfitCenterId;
	
	

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="profit_center_name")
	private String profitCenterName;

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
	// @JsonBackReference
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="parent_org_id")
	//@JsonIgnore
	//private TGsOrganaization TGsOrganaization;

	public TGsProfitCenter() {
	}

	public int getGsProfitCenterId() {
		return this.gsProfitCenterId;
	}

	public void setGsProfitCenterId(int gsProfitCenterId) {
		this.gsProfitCenterId = gsProfitCenterId;
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

	public String getProfitCenterName() {
		return this.profitCenterName;
	}

	public void setProfitCenterName(String profitCenterName) {
		this.profitCenterName = profitCenterName;
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

	/*public TGsOrganaization getTGsOrganaization() {
		return this.TGsOrganaization;
	}

	public void setTGsOrganaization(TGsOrganaization TGsOrganaization) {
		this.TGsOrganaization = TGsOrganaization;
	}
*/
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
	
	

}