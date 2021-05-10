package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureGLHead {

	private int expenditureGlHeadId;
	private String orgName;
	private int orgId;
	private int parentOrgId;
	private String Status;
	
	private int userId;
	private String expenditureGlHeadName;
	private String status=GsAppConstants.ACTIVE;
	private String createdBy;
	private String updtedBy;
}
