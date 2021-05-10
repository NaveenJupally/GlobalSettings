package com.automate.model.globalsettings;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vertical {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int verticalId;

	//private Timestamp createdAt;

	private String createdBy;

	private String orgDesc;

	private String orgLogo;

	private String orgName;

	//private Timestamp updatedAt;

	private String updatedBy;

	//private GlobalOrg globalOrg;
	
	private int parentOrgId;

	private AddressModel addressModels;

	private ContactDetails contactDetails;

}
