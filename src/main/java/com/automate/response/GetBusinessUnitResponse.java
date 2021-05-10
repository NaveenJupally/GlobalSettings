package com.automate.response;

import java.util.List;

import com.automate.model.globalsettings.BusinessUnit;

/**
 * @author sindh
 *
 */
public class GetBusinessUnitResponse extends BaseResponse {

	private List<BusinessUnit> businessUnits;

	public List<BusinessUnit> getBusinessUnits() {
		return businessUnits;
	}

	public void setBusinessUnits(List<BusinessUnit> businessUnits) {
		this.businessUnits = businessUnits;
	}

}
