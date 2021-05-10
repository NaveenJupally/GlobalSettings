package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_doccument_range_configuration database table.
 * 
 */
@Entity
@Table(name="t_gs_doccument_range_configuration")
@NamedQuery(name="TGsDoccumentRangeConfiguration.findAll", query="SELECT t FROM TGsDoccumentRangeConfiguration t")
public class TGsDoccumentRangeConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="doccument_range_configuration_id")
	private int doccumentRangeConfigurationId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="end_range")
	private String endRange;

	@Column(name="module_id")
	private String moduleId;

	@Column(name="start_range")
	private String startRange;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	public TGsDoccumentRangeConfiguration() {
	}

	public int getDoccumentRangeConfigurationId() {
		return this.doccumentRangeConfigurationId;
	}

	public void setDoccumentRangeConfigurationId(int doccumentRangeConfigurationId) {
		this.doccumentRangeConfigurationId = doccumentRangeConfigurationId;
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

	public String getEndRange() {
		return this.endRange;
	}

	public void setEndRange(String endRange) {
		this.endRange = endRange;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getStartRange() {
		return this.startRange;
	}

	public void setStartRange(String startRange) {
		this.startRange = startRange;
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