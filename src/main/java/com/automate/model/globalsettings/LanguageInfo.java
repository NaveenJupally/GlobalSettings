package com.automate.model.globalsettings;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageInfo {

	private List<Language> languages;
	//private int parentOrgId;
	
	
}
