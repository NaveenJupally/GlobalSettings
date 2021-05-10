package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetGroup {

	private int assetGroupId;
	private int orgId;
	private String assetClassName;
	private String assetNature;
	private String status=GsAppConstants.ACTIVE;
	private int userId;
	private int parentOrgId;
}
