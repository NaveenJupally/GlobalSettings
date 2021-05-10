package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostCenter {

	private int costCenterId;
	private String orgName;
	private int parentOrgId;
	private int userId;
	private String costCenterName;
	private String status=GsAppConstants.ACTIVE;
}
