package com.automate.model.globalsettings;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicWorkflow {
	
	private int parentOrgId;
	private int verticalId;
	private int businessUnitId;
	private int moduleId;
	private int subModulId;
	private List<Approver> approverList;
	private String createdBy;
	private String updatedBy;
	
}
