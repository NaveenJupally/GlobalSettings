package com.automate.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Director {

	private String dirFirstName;
	private String dirLastName;
	private String dirDesignation;
	private String dirContactNo;
	private String dirEmailId;
	private String dirPanNo;
	private String dirAddressLine;
	private String dirPinCode;

}
