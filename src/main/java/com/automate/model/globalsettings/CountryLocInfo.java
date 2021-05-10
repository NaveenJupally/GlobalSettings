package com.automate.model.globalsettings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sindh
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryLocInfo {
	private int id;
	private String city;
	private String state;
	private String pincode;
	private String mandal;
	private String location;
	private String createdBy;
	private String updatedBy;

}
