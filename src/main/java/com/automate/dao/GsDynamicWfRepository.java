package com.automate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsDynamicWorkflow;

public interface GsDynamicWfRepository extends JpaRepository<TGsDynamicWorkflow, Integer> {

}
