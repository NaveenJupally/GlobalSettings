package com.automate.request;

import com.automate.model.globalsettings.Vertical;

public class UpdateVerticalRequest extends BaseRequest {

	private Vertical verticalInfo;

	public Vertical getVerticalInfo() {
		return verticalInfo;
	}

	public void setVerticalInfo(Vertical verticalInfo) {
		this.verticalInfo = verticalInfo;
	}

}
