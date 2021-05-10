package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.OrgMapping;

public interface OrgMappingRepo extends JpaRepository<OrgMapping, Integer> {

//	public List<OrgMapping> findByTGsUser_userid(int userId);

	public List<OrgMapping> findByParentOrgId(int parentOrgId);
}
