package com.automate.model.globalsettings;

import java.util.List;

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
public class Country {

	private int countryId;
	private String country;
	private List<CountryLocInfo> countryLocs;
	private String createdBy;
	private String updatedBy;

}
