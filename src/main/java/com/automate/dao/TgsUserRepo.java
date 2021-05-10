package com.automate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsUser;

public interface TgsUserRepo extends JpaRepository<TGsUser, Integer>{

	public TGsUser findByuseridAndParentOrgId(int userId, int parentOrgId);
	
	public TGsUser findByUsernameAndPassword(String username,  String password);

}
