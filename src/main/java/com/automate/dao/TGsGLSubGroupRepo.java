package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsGlSubGroup;


public interface TGsGLSubGroupRepo extends JpaRepository<TGsGlSubGroup, Integer> {

	List<TGsGlSubGroup> findByUserIdAndParentOrgId(int userId, int parentOrgId);

}
