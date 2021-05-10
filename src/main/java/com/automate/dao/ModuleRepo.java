package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsModule;

public interface ModuleRepo extends JpaRepository<TGsModule, Integer> {

	TGsModule findByGsModuleId(int id);

	List<TGsModule> findByGsModuleIdIn(List<Integer> moduleIds);

}
