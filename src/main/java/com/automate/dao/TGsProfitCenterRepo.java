package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsProfitCenter;


public interface TGsProfitCenterRepo extends JpaRepository<TGsProfitCenter, Integer> {

	List<TGsProfitCenter> findByUserIdAndParentOrgId(int userId, int parentOrgId);

}
