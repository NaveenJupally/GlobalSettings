package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_language database table.
 * 
 */
@Entity
@Table(name="t_gs_language")
@NamedQuery(name="TGsLanguage.findAll", query="SELECT t FROM TGsLanguage t")
public class TGsLanguage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_language_id")
	private int gsLanguageId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "parent_org_id")
	private int parentOrgId;

	
	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	private String currency;

	private String language;

	@Column(name="select_country")
	private String selectCountry;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name = "status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public TGsLanguage() {
	}

	public int getGsLanguageId() {
		return this.gsLanguageId;
	}

	public void setGsLanguageId(int gsLanguageId) {
		this.gsLanguageId = gsLanguageId;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSelectCountry() {
		return this.selectCountry;
	}

	public void setSelectCountry(String selectCountry) {
		this.selectCountry = selectCountry;
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

	

}