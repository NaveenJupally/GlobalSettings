package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsVertical;

public interface VerticalRepo extends JpaRepository<TGsVertical, Integer> {

	//public List<TGsVertical> findByTGsOrganaization_parentOrgId(int orgId);
	public List<TGsVertical> findBytGsOrganaization_parentOrgId(int orgId);

}
