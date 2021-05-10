package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the t_gs_rule_configuration database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_gs_rule_configuration")
@NamedQuery(name = "TGsRuleConfiguration.findAll", query = "SELECT t FROM TGsRuleConfiguration t")
public class TGsRuleConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "gs_rule_configuration_id")
	private int ruleConfigurationId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "parent_org_id")
	private int parentOrgId;
	
	@Column(name = "approver_name")
	private String approverName;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	private String department;

	@Column(name = "required_approvers")
	private String requiredApprovers;

	@Column(name = "rule_condition")
	private String ruleCondition;

	

	@Column(name = "rule_name")
	private String ruleName;

	@Column(name="threshold")
	private String threshold;

	@Column(name="type")
	private String type;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "status")
	private String status;
	
	
	

}