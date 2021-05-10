package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiabilityGLSubHead {

	private int gsLiabilityGlSubheadId;
	private String orgName;
	//private int orgId;
	private String liabilityGlHead;
	private String liabilityGLSubHeadName;
	private String status=GsAppConstants.ACTIVE;
	private int userId;
	private int parentOrgId;
	
}
