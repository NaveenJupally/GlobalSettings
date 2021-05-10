package com.automate.model.globalsettings;

import com.automate.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Login {
	
	private int userId;
	private int parentOrgId;
	private Status status;
	

}
