package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GLSubGroup {

	private int glSubGroupId;
	private int parentOrgId;
	private String glGroupName;
	private String glSubGropName;
	private String orgName;
	private int orgId;
	private int userId;
	private String status=GsAppConstants.ACTIVE;
}
