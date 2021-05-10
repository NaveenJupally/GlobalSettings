package com.automate.model.globalsettings;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {
	private int gsLanguageId;
	private int parentOrgId;
	private String selectCountry;
	private String language;
	private String currency;
	private List<String> languageInfo;

	private String createdBy;
	private String updatedBy;

}
