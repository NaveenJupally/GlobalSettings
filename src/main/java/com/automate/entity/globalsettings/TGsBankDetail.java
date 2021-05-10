package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_bank_details database table.
 * 
 */
@Entity
@Table(name="t_gs_bank_details")
@NamedQuery(name="TGsBankDetail.findAll", query="SELECT t FROM TGsBankDetail t")
public class TGsBankDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_bank_details_id")
	private int gsBankDetailsId;

	@Column(name="account_no")
	private String accountNo;

	private String address;

	@Column(name="bank_name")
	private String bankName;

	private String branch;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="ifsc_code")
	private String ifscCode;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	private String upload;

	public TGsBankDetail() {
	}

	public int getGsBankDetailsId() {
		return this.gsBankDetailsId;
	}

	public void setGsBankDetailsId(int gsBankDetailsId) {
		this.gsBankDetailsId = gsBankDetailsId;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public String getIfscCode() {
		return this.ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
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

	public String getUpload() {
		return this.upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

}