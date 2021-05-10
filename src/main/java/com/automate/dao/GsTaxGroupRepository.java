package com.automate.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.automate.entity.globalsettings.TGsTaxGroup;

public interface GsTaxGroupRepository extends PagingAndSortingRepository<TGsTaxGroup, Long>{

}
