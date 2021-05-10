package com.automate.model.globalsettings;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private int addressId;
	private String city;
	private String country;
	private String createdBy;
	private String correspondenceAddCheckbox;
	private CommunicationAddr communicationAddress;
	private CorrespondenceAddr correspondenceAddress;
	private String location;
	private String mandal;
	private String pincode;
	private String state;
	private String updatedBy;
	
}
