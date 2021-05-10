package com.automate.request;

import com.automate.model.globalsettings.BusinessUnit;

/**
 * @author sindh
 *
 */
public class UpdateBusinessUnitRequest extends BaseRequest {

	private BusinessUnit businessUnit;

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

}
