package com.automate.model.globalsettings;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryInfo {

	private int parentOrgId;
	private int userId;
	private List<Country> countryInfo;


}
