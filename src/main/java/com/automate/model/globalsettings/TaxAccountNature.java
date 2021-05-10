package com.automate.model.globalsettings;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxAccountNature {

	private String id;
	private String taxNature;
	private String taxGroup;
	private String taxAccount;
	private String taxAccountType;
	private String status;
}
