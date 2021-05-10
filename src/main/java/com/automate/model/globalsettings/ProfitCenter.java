package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfitCenter {

	private int profitCenterId;
	private String orgName;
	//private int orgId;
	private int parentOrgId;
	private int userId;
	private String profitCenterName;
	//private String activeStatus;
    private String status=GsAppConstants.ACTIVE;
}
