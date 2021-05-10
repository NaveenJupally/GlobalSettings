package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsOrganaization;

public interface OrganaizationRepo extends JpaRepository<TGsOrganaization, Integer> {
	public TGsOrganaization findByParentOrgId(int orgId);

	public List<TGsOrganaization> findByParentOrgIdIn(List<Integer> orgIds);
}
