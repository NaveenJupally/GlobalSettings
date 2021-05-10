package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsGlAccount;


public interface TGsGLAccountRepo extends JpaRepository<TGsGlAccount, Integer> {

	List<TGsGlAccount> findByUserIdAndParentOrgId(int userId, int parentOrgId);

}
