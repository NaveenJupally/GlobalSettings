package com.automate.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.automate.entity.globalsettings.TGsTaxMapping;

public interface GsTaxMappingRepository extends PagingAndSortingRepository<TGsTaxMapping, Long> {

}
