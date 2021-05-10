package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_managerial_manpower database table.
 * 
 */
@Entity
@Table(name="t_gs_managerial_manpower")
@NamedQuery(name="TGsManagerialManpower.findAll", query="SELECT t FROM TGsManagerialManpower t")
public class TGsManagerialManpower implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_managerial_manpower_id")
	private int gsManagerialManpowerId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="designation_name")
	private String designationName;

	@Column(name="hierarchy_level")
	private String hierarchyLevel;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	public TGsManagerialManpower() {
	}

	public int getGsManagerialManpowerId() {
		return this.gsManagerialManpowerId;
	}

	public void setGsManagerialManpowerId(int gsManagerialManpowerId) {
		this.gsManagerialManpowerId = gsManagerialManpowerId;
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

	public String getDesignationName() {
		return this.designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getHierarchyLevel() {
		return this.hierarchyLevel;
	}

	public void setHierarchyLevel(String hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
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