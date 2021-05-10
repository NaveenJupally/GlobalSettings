package com.automate.entity.globalsettings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_gs_organaization_addresses1")
public class OrgAddrMappings {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parent_org_id")
	private int parentOrgId;
	
	@Column(name="tgs_organaization_parent_org_id")
	private Integer orgId;
	
	@Column(name="addresses_address_id")
	private Integer addrId;

}
