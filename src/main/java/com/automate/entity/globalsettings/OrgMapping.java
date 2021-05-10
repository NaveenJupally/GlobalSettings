package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the org_mappings database table.
 * 
 */
@Entity
@Table(name = "org_mappings")
@NamedQuery(name = "OrgMapping.findAll", query = "SELECT o FROM OrgMapping o")
public class OrgMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "module_mapping_id")
	private int moduleMappingId;

	@Column(name = "relation_id")
	private int relationId;

	@Column(name = "org_id")
	private int orgId;

	@Column(name = "relation_name")
	private String relationName;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "updated_by")
	private String updatedBy;

//	//bi-directional many-to-one association to TGsOrganaization
//	@ManyToOne
//	@JoinColumn(name="parent_org_id")
//	private TGsOrganaization TGsOrganaization;

	@Column(name = "parent_org_id")
	private int parentOrgId;

	@Column(name = "user_id")
	private int userId;

	public OrgMapping() {
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return this.id;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getModuleMappingId() {
		return moduleMappingId;
	}

	public void setModuleMappingId(int moduleMappingId) {
		this.moduleMappingId = moduleMappingId;
	}

	public int getRelationId() {
		return this.relationId;
	}

	public void setRelationId(int relationId) {
		this.relationId = relationId;
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

	public int getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(int parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

//	public TGsOrganaization getTGsOrganaization() {
//		return this.TGsOrganaization;
//	}
//
//	public void setTGsOrganaization(TGsOrganaization TGsOrganaization) {
//		this.TGsOrganaization = TGsOrganaization;
//	}

}