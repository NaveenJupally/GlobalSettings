package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the t_gs_country database table.
 * 
 */
@Entity
@Table(name = "t_gs_country")
@NamedQuery(name = "TGsCountry.findAll", query = "SELECT t FROM TGsCountry t")
public class TGsCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "gs_country_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gsCountryId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "parent_org_id")
	private int parentOrgId;

	private String country;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "TGsCountry", cascade = CascadeType.ALL)
	private List<CountryLocation> countryLocations;

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

	public TGsCountry() {
	}

	public List<CountryLocation> getCountryLocations() {
		return countryLocations;
	}

	public void setCountryLocations(List<CountryLocation> countryLocations) {
		this.countryLocations = countryLocations;
	}

	public int getGsCountryId() {
		return this.gsCountryId;
	}

	public void setGsCountryId(int gsCountryId) {
		this.gsCountryId = gsCountryId;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

}