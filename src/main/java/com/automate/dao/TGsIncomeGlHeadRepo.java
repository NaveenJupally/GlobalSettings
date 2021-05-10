package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsIncomeGlHead;


public interface TGsIncomeGlHeadRepo extends JpaRepository<TGsIncomeGlHead, Integer> {

	List<TGsIncomeGlHead> findByUserIdAndParentOrgId(int userId, int parentOrgId);
	

}
