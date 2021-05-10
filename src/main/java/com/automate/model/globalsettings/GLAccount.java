package com.automate.model.globalsettings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GLAccount {
	
	private Long id;
	private String glAcctName;
	private String glGroupName;
	private String glSubGroupName;
	private String glVisibleToVendor;
	private String createdBy;
	private String updatedBy;
	private String status;
	


}
