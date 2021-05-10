package com.automate.model.globalsettings;

import java.sql.Timestamp;
import java.util.List;

import com.automate.entity.globalsettings.TGsUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalOrg {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int orgId;

	//private Timestamp createdAt;

	private String createdBy;

	private String orgDesc;

	private String orgLogo;

	private String orgName;

	//private Timestamp updatedAt;

	private String updatedBy;

	private int userId;

	//private List<Vertical> verticals;

	private AddressModel addressModels;

	private ContactDetails contactDetails;

}
