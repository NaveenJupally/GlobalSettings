package com.automate.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.automate.entity.globalsettings.TGsTaxAccount;


public interface GsTaxAccountRepository extends PagingAndSortingRepository<TGsTaxAccount, Long> {

}
