package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiabilityGLHead {

	private int liabilityGLHeadId;
	private String orgName;
	//private int orgId;
	private String liabilityGLHeadName;
	private String status=GsAppConstants.ACTIVE;
	private int userId;
	private int parentOrgId;
}
