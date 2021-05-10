package com.automate.model.globalsettings;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetails {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int contactId;

	private String contactNumber;

	private String googleLocation;

	private String gstNo;

	private String panNo;

	private String primaryContactEmailId;

	private String primaryContactNo;

	private String primaryContactPerson;

	private String secondaryContactEmailId1;

	private String secondaryContactEmailId2;

	private String secondaryContactNo1;

	private String secondaryContactNo2;

	private String secondaryContactPerson1;

	private String secondaryContactPerson2;

	private String webSiteUrl;

	//private GlobalOrg globalOrg;

	//private Vertical vertical;

}
