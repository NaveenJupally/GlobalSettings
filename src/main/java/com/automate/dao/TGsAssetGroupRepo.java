package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsAssetGroup;


public interface TGsAssetGroupRepo extends JpaRepository<TGsAssetGroup, Integer> {

	List<TGsAssetGroup> findByUserIdAndParentOrgId(int userId, int parentOrgId);

}
