package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_rule database table.
 * 
 */
@Entity
@Table(name="t_gs_rule")
@NamedQuery(name="TGsRule.findAll", query="SELECT t FROM TGsRule t")
public class TGsRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_rule_id")
	private int gsRuleId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	private String rule1;

	private String rule2;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	public TGsRule() {
	}

	public int getGsRuleId() {
		return this.gsRuleId;
	}

	public void setGsRuleId(int gsRuleId) {
		this.gsRuleId = gsRuleId;
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

	public String getRule1() {
		return this.rule1;
	}

	public void setRule1(String rule1) {
		this.rule1 = rule1;
	}

	public String getRule2() {
		return this.rule2;
	}

	public void setRule2(String rule2) {
		this.rule2 = rule2;
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