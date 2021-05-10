package com.automate.model.globalsettings;

import java.util.Map;

import com.automate.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUnits {

	private Map<Integer, Map<Integer, String>> businessUnits;
	private Status status;

}
