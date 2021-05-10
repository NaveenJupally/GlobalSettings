package com.automate.model.globalsettings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxAccount {
	
	
	
	private Long gsTaxAccountId;
	private int parentOrgId;
	private int verticalId;
	private int businessUnitId;
	private int userId;
	private String accountType;
	private String createdBy;
	private String taxAccount;
	private String taxAccountName;
	private String taxGroup;
	private String taxNature;
	private String updatedBy;
	private String status;

}
