package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetNature {

	private int assetNatureId;
	private int parentOrgId;
	private String orgName;
	//private int orgId;
	private String assetNatureName;
	private String assetGroup;
	private int userId;
	private String status;
	//=GsAppConstants.ACTIVE;
	
}
