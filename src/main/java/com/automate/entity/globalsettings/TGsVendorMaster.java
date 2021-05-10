package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_vendor_master database table.
 * 
 */
@Entity
@Table(name="t_gs_vendor_master")
@NamedQuery(name="TGsVendorMaster.findAll", query="SELECT t FROM TGsVendorMaster t")
public class TGsVendorMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_vendor_master_id")
	private int gsVendorMasterId;

	@Column(name="bank_details_id")
	private int bankDetailsId;

	@Column(name="company_info_id")
	private int companyInfoId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="director_id")
	private int directorId;

	@Column(name="select_org")
	private String selectOrg;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	public TGsVendorMaster() {
	}

	public int getGsVendorMasterId() {
		return this.gsVendorMasterId;
	}

	public void setGsVendorMasterId(int gsVendorMasterId) {
		this.gsVendorMasterId = gsVendorMasterId;
	}

	public int getBankDetailsId() {
		return this.bankDetailsId;
	}

	public void setBankDetailsId(int bankDetailsId) {
		this.bankDetailsId = bankDetailsId;
	}

	public int getCompanyInfoId() {
		return this.companyInfoId;
	}

	public void setCompanyInfoId(int companyInfoId) {
		this.companyInfoId = companyInfoId;
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

	public int getDirectorId() {
		return this.directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}

	public String getSelectOrg() {
		return this.selectOrg;
	}

	public void setSelectOrg(String selectOrg) {
		this.selectOrg = selectOrg;
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

}