package com.automate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
