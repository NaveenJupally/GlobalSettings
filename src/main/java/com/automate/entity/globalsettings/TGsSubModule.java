package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the t_gs_sub_module database table.
 * 
 */
@Entity
@Table(name = "t_gs_sub_module")
@NamedQuery(name = "TGsSubModule.findAll", query = "SELECT t FROM TGsSubModule t")
public class TGsSubModule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "gs_sub_module_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gsSubModuleId;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "sub_module_name")
	private String subModuleName;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "updated_by")
	private String updatedBy;

	// bi-directional many-to-one association to ModuleMapping
	@OneToOne(mappedBy = "TGsSubModule")
	private ModuleMapping moduleMappings;

	// bi-directional many-to-one association to TGsModule
	@ManyToOne
	@JoinColumn(name = "module_id")
	private TGsModule TGsModule;

	public TGsSubModule() {
	}

	public int getGsSubModuleId() {
		return this.gsSubModuleId;
	}

	public void setGsSubModuleId(int gsSubModuleId) {
		this.gsSubModuleId = gsSubModuleId;
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

	public String getSubModuleName() {
		return this.subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
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

	public ModuleMapping getModuleMappings() {
		return this.moduleMappings;
	}

	public void setModuleMappings(ModuleMapping moduleMappings) {
		this.moduleMappings = moduleMappings;
	}

	public TGsModule getTGsModule() {
		return this.TGsModule;
	}

	public void setTGsModule(TGsModule TGsModule) {
		this.TGsModule = TGsModule;
	}

}