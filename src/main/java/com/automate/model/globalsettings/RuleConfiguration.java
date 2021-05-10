package com.automate.model.globalsettings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleConfiguration {

	private int ruleConfigurationId;
	private int parentOrgId;
	private int userId;
	private String ruleName;
	private String ruleCondition;
	private String threshold;
	private String department;
	private String type;
	private String approversRequired;
	private String approvalName;
	private int moduleId;
	private int subModuleId;
	private int ruleId;
	private String status;
	private int errorCode;
	private String createdBy;
	private String updatedBy;

}
