package com.automate.model.globalsettings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TaxMapping {

	
	private Long gsTaxMappingId;
	private int parentOrgId;
	private int verticalId;
	private int businessUnitId;
	private int userId;
	private String serviceType;
	private String vendorType;
	private String taxGroup;
	private String supplyType;
	private String taxAccount;
	private String taxSection;
	private String taxRatePerc;
	private String effectiveFrom;
	private String effectiveTo;
	private String createdBy;
	private String updatedBy;
}
