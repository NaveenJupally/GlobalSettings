package com.automate.model.globalsettings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxGroup {

private String gs_tax_group_id;
	private int parentOrgId;
	private int verticalId;
	private int businessUnitId;
	private int userId;
	private String select_org;
	private String group_name;
	private String created_by;
	private String updated_by;
}
