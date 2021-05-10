package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsCostCenter;


public interface TGsCostCenterRepo extends JpaRepository<TGsCostCenter, Integer> {

	List<TGsCostCenter> findByUserIdAndParentOrgId(int userId, int parentOrgId);

}
