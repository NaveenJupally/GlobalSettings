package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GLGroup {

	private int gsGlGroupId;
	private String orgName;
	private int orgId;
	private String glGropName;
	private String accountType;
	private String glHead;
	private String status=GsAppConstants.ACTIVE;
	private int userId;
	private int parentOrgId;
}
