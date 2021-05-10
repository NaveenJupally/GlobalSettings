package com.automate.request;

import java.util.List;

import com.automate.model.globalsettings.BusinessUnit;

public class SaveBusinessUnitRequest extends BaseRequest {

	List<BusinessUnit> businessUnits;

	public List<BusinessUnit> getBusinessUnits() {
		return businessUnits;
	}

	public void setBusinessUnits(List<BusinessUnit> businessUnits) {
		this.businessUnits = businessUnits;
	}

}
