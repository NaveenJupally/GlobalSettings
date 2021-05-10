package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.automate.entity.globalsettings.TGsRuleConfiguration;

public interface RuleConfigurationRepo extends JpaRepository<TGsRuleConfiguration, Integer> {

	@Query(value = "SELECT * FROM t_gs_rule_configuration  where  parent_org_id = :id and user_id =:userid",nativeQuery = true)
	public List<TGsRuleConfiguration> findByParentOrgIdAndUserId(@Param(value = "id") int parentOrgId,
			@Param(value = "userid") int userId);
}
