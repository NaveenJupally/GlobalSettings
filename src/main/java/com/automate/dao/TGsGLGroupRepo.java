package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsGlGroup;


public interface TGsGLGroupRepo extends JpaRepository<TGsGlGroup, Integer> {

	List<TGsGlGroup> findByUserIdAndParentOrgId(int userId, int parentOrgId);

}
