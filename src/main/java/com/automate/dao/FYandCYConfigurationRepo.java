package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.ModuleMapping;

/**
 * @author sindh
 *
 */
public interface FYandCYConfigurationRepo extends JpaRepository<ModuleMapping, Integer> {

	public List<ModuleMapping> findByTGsOrganaization_parentOrgId(int orgId);

}
