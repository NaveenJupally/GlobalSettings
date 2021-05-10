package com.automate.model.globalsettings;

import com.automate.constants.GsAppConstants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxSection {
	private String id;
	private int parentOrgId;
	private int verticalId;
	private int businessUnitId;
	private int userId;
	
	private String taxSectionName;
	private String status;
	
	
}
