package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsCountry;
import com.automate.entity.globalsettings.TGsExpenditureGlHead;
import com.automate.entity.globalsettings.TGsProfitCenter;


public interface TGsExpenditureGlHeadRepo extends JpaRepository<TGsExpenditureGlHead, Integer> {
	

	

	public List<TGsExpenditureGlHead> findByUserIdAndParentOrgId(int userId, int parentOrgId);
}


