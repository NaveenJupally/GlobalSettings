package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.automate.entity.globalsettings.TGsBusinessUnit;

@Repository
public interface BusinessUnitRepo extends JpaRepository<TGsBusinessUnit, Integer> {

	public TGsBusinessUnit findByGsBusinessUnitId(int businessUnitId);

	// public List<TGsBusinessUnit> findByTGsVertical_gsVerticalId(int verticalId);

	@Query(value = "SELECT * FROM t_gs_business_unit WHERE parent_vertical_id in( :verticalid)", nativeQuery = true)
	public List<TGsBusinessUnit> findBusinessUnits(@Param(value = "verticalid") List<Integer> verticalId);
}
