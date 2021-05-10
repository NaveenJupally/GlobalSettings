package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the module_mappings database table.
 * 
 */
@Entity
@Table(name = "module_mappings")
@NamedQuery(name = "ModuleMapping.findAll", query = "SELECT m FROM ModuleMapping m")
public class ModuleMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "relation_id")
	private int relationId;

	@Column(name = "relation_name")
	private String relationName;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "status")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// bi-directional many-to-one association to TGsModule
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "module_id")
	private TGsModule TGsModule;

	// bi-directional many-to-one association to TGsSubModule
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_module_id")
	private TGsSubModule TGsSubModule;

	// bi-directional many-to-one association to TGsOrganaization
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_org_id")
	private TGsOrganaization TGsOrganaization;

	public ModuleMapping() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getRelationId() {
		return this.relationId;
	}

	public void setRelationId(int relationId) {
		this.relationId = relationId;
	}

	public String getRelationName() {
		return this.relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
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

	public TGsModule getTGsModule() {
		return this.TGsModule;
	}

	public void setTGsModule(TGsModule TGsModule) {
		this.TGsModule = TGsModule;
	}

	public TGsSubModule getTGsSubModule() {
		return this.TGsSubModule;
	}

	public void setTGsSubModule(TGsSubModule TGsSubModule) {
		this.TGsSubModule = TGsSubModule;
	}

	public TGsOrganaization getTGsOrganaization() {
		return this.TGsOrganaization;
	}

	public void setTGsOrganaization(TGsOrganaization TGsOrganaization) {
		this.TGsOrganaization = TGsOrganaization;
	}

}