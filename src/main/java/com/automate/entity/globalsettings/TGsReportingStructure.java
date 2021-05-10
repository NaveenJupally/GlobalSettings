package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_reporting_structure database table.
 * 
 */
@Entity
@Table(name="t_gs_reporting_structure")
@NamedQuery(name="TGsReportingStructure.findAll", query="SELECT t FROM TGsReportingStructure t")
public class TGsReportingStructure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_reporting_structure_id")
	private int gsReportingStructureId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="level_name")
	private String levelName;

	@Column(name="level_sequence")
	private String levelSequence;

	@Column(name="managerial_man_power")
	private String managerialManPower;

	@Column(name="total_man_power")
	private String totalManPower;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	public TGsReportingStructure() {
	}

	public int getGsReportingStructureId() {
		return this.gsReportingStructureId;
	}

	public void setGsReportingStructureId(int gsReportingStructureId) {
		this.gsReportingStructureId = gsReportingStructureId;
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

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelSequence() {
		return this.levelSequence;
	}

	public void setLevelSequence(String levelSequence) {
		this.levelSequence = levelSequence;
	}

	public String getManagerialManPower() {
		return this.managerialManPower;
	}

	public void setManagerialManPower(String managerialManPower) {
		this.managerialManPower = managerialManPower;
	}

	public String getTotalManPower() {
		return this.totalManPower;
	}

	public void setTotalManPower(String totalManPower) {
		this.totalManPower = totalManPower;
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