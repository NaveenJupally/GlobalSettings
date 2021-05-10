package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_org_structure database table.
 * 
 */
@Entity
@Table(name="t_gs_org_structure")
@NamedQuery(name="TGsOrgStructure.findAll", query="SELECT t FROM TGsOrgStructure t")
public class TGsOrgStructure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_org_structure_id")
	private int gsOrgStructureId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="level_headcount")
	private String levelHeadcount;

	@Column(name="level_name")
	private String levelName;

	@Column(name="`level_name_count`")
	private String levelName_Count;

	@Column(name="level_name_headcount")
	private String levelNameHeadcount;

	@Column(name="level_sequence")
	private String levelSequence;

	@Column(name="org_name")
	private String orgName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	public TGsOrgStructure() {
	}

	public int getGsOrgStructureId() {
		return this.gsOrgStructureId;
	}

	public void setGsOrgStructureId(int gsOrgStructureId) {
		this.gsOrgStructureId = gsOrgStructureId;
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

	public String getLevelHeadcount() {
		return this.levelHeadcount;
	}

	public void setLevelHeadcount(String levelHeadcount) {
		this.levelHeadcount = levelHeadcount;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelName_Count() {
		return this.levelName_Count;
	}

	public void setLevelName_Count(String levelName_Count) {
		this.levelName_Count = levelName_Count;
	}

	public String getLevelNameHeadcount() {
		return this.levelNameHeadcount;
	}

	public void setLevelNameHeadcount(String levelNameHeadcount) {
		this.levelNameHeadcount = levelNameHeadcount;
	}

	public String getLevelSequence() {
		return this.levelSequence;
	}

	public void setLevelSequence(String levelSequence) {
		this.levelSequence = levelSequence;
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

}