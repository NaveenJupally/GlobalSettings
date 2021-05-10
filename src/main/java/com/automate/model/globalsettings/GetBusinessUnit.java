package com.automate.model.globalsettings;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBusinessUnit {

	private int userId;
	private int parentOrgId;
	private List<Integer> verticalIds;

}