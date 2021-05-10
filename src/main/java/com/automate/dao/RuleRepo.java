package com.automate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsRule;

public interface RuleRepo extends JpaRepository<TGsRule, Integer> {

}
