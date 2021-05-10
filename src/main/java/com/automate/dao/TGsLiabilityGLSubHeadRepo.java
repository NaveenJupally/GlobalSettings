package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsLiabilityGlSubhead;


public interface TGsLiabilityGLSubHeadRepo extends JpaRepository<TGsLiabilityGlSubhead, Integer> {

	List<TGsLiabilityGlSubhead> findByUserIdAndParentOrgId(int userId, int parentOrgId);

}
