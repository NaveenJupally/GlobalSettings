package com.automate.request;

import java.util.List;

import com.automate.model.globalsettings.Vertical;



public class SaveVerticalRequest extends BaseRequest {

	private List<Vertical> verticals;

	public List<Vertical> getVerticals() {
		return verticals;
	}

	public void setVerticals(List<Vertical> verticals) {
		this.verticals = verticals;
	}

}
