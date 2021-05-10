package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeGLHead {

	private int incomeGlHeadId;
	private String orgName;
	//private int orgId;
	private int parentOrgId;
	private int userId;
	private String incomeGlHeadName;
	private String Status;
	private String status=GsAppConstants.ACTIVE;
}
