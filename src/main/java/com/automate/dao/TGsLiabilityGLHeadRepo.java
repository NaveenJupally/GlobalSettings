package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsLiabilityGlHead;


public interface TGsLiabilityGLHeadRepo extends JpaRepository<TGsLiabilityGlHead, Integer> {

	List<TGsLiabilityGlHead> findByUserIdAndParentOrgId(int userId, int parentOrgId);

}
