package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsCountry;

public interface CountryRepo extends JpaRepository<TGsCountry, Integer> {

	public TGsCountry findByGsCountryId(int id);

	public List<TGsCountry> findByParentOrgIdAndUserId(int parentOrgId, int userId);
}
