package com.automate.response;

import java.util.List;

import com.automate.model.globalsettings.Vertical;

public class GetVerticalResponse extends BaseResponse {

	private List<Vertical> verticalInfo;

	public List<Vertical> getVerticalInfo() {
		return verticalInfo;
	}

	public void setVerticalInfo(List<Vertical> verticalInfo) {
		this.verticalInfo = verticalInfo;
	}

}
