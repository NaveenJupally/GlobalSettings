package com.automate.model.globalsettings;

import java.util.Map;

import com.automate.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrganisation {

	// private Map<Integer, String> organationNames;
	private int orgId;
	private String orgName;
	private Status status;

}