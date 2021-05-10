package com.automate.model.globalsettings;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUnit {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int businessUnitId;

	private String businessUnitDesc;

	private String businessUnitLogo;

	private String businessUnitName;

	private String createdBy;

	//private String dealerCode;

	private String googleLocation;

	private String oemType;

	private String parentDealerCode;

	private String parentDealerName;

	private AddressModel addressModels;

	private List<SubDealar> subDealarList;

	private String updatedBy;

	private String websiteUrl;

	private ContactDetails contactDetails;

	private int parentOrgId;

	private int gsVerticalId;

	private int userId;

	private AddressModel businessUnitAddresses;

}
