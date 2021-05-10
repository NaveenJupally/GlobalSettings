package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the t_gs_module database table.
 * 
 */
@Entity
@Table(name = "t_gs_module")
@NamedQuery(name = "TGsModule.findAll", query = "SELECT t FROM TGsModule t")
public class TGsModule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "gs_module_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gsModuleId;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "module_name")
	private String moduleName;

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

	// bi-directional many-to-one association to ModuleMapping
	@OneToMany(mappedBy = "TGsModule", cascade = CascadeType.ALL)
	private List<ModuleMapping> moduleMappings;

	// bi-directional many-to-one association to TGsSubModule
	@OneToMany(mappedBy = "TGsModule" , cascade = CascadeType.ALL)
	private List<TGsSubModule> TGsSubModules;

	public TGsModule() {
	}

	public int getGsModuleId() {
		return this.gsModuleId;
	}

	public void setGsModuleId(int gsModuleId) {
		this.gsModuleId = gsModuleId;
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

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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

	public List<ModuleMapping> getModuleMappings() {
		return this.moduleMappings;
	}

	public void setModuleMappings(List<ModuleMapping> moduleMappings) {
		this.moduleMappings = moduleMappings;
	}

	public ModuleMapping addModuleMapping(ModuleMapping moduleMapping) {
		getModuleMappings().add(moduleMapping);
		moduleMapping.setTGsModule(this);

		return moduleMapping;
	}

	public ModuleMapping removeModuleMapping(ModuleMapping moduleMapping) {
		getModuleMappings().remove(moduleMapping);
		moduleMapping.setTGsModule(null);

		return moduleMapping;
	}

	public List<TGsSubModule> getTGsSubModules() {
		return this.TGsSubModules;
	}

	public void setTGsSubModules(List<TGsSubModule> TGsSubModules) {
		this.TGsSubModules = TGsSubModules;
	}

	public TGsSubModule addTGsSubModule(TGsSubModule TGsSubModule) {
		getTGsSubModules().add(TGsSubModule);
		TGsSubModule.setTGsModule(this);

		return TGsSubModule;
	}

	public TGsSubModule removeTGsSubModule(TGsSubModule TGsSubModule) {
		getTGsSubModules().remove(TGsSubModule);
		TGsSubModule.setTGsModule(null);

		return TGsSubModule;
	}

}