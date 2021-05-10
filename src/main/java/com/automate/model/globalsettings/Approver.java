package com.automate.model.globalsettings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Approver {

	private String approverName;
	private String approverId;
	private String approverType;
}
