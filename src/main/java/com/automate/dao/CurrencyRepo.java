package com.automate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsCurrency;

public interface CurrencyRepo extends JpaRepository<TGsCurrency, Integer> {

	public TGsCurrency findByGsCurrencyId(int id);

	public List<TGsCurrency> findByGsCurrencyIdIn(List<Integer> currencyIds);

	public List<TGsCurrency> findByParentOrgIdAndUserId(int parentOrgId, int userId);


}
