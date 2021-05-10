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
public class CurrencyCountryInfo {
	private int gsCurrencyId;
	private String country;
	private String currency;
	private String createdBy;
	private String updatedBy;

}

