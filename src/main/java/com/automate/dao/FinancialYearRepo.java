package com.automate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsFinancialYear;

public interface FinancialYearRepo extends JpaRepository<TGsFinancialYear, Integer> {

	TGsFinancialYear findByGsFinancialYearId(int id);

	TGsFinancialYear findByParentOrgIdAndUserId(int gsOrgid, int userId);



}
