package com.automate.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsModule;

public interface GsModuleRepo extends JpaRepository<TGsModule, Integer> {



}
