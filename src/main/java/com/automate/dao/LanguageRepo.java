package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsLanguage;

public interface LanguageRepo extends JpaRepository<TGsLanguage, Integer> {

	TGsLanguage findByGsLanguageId(int id);

	public List<TGsLanguage> findByGsLanguageIdIn(List<Integer> languageIds);

	public List<TGsLanguage> findByParentOrgIdAndUserId(int parentOrgId,int userId);

}
