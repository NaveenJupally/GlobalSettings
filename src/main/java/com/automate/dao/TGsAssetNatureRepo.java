package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsAssetNature;


public interface TGsAssetNatureRepo extends JpaRepository<TGsAssetNature, Integer> {

	List<TGsAssetNature> findByUserIdAndParentOrgId(int userId, int parentOrgId);

}
