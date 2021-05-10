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

public class Currency {

	private int parentOrgId;
	private int userId;
	private List<CurrencyCountryInfo> curAndCntryInfo;
	private String createdBy;
	private String updatedBy;

}
