package com.automate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsSubModule;

public interface SubModuleRepo extends JpaRepository<TGsSubModule, Integer> {

	public TGsSubModule findByGsSubModuleId(int id);
}
