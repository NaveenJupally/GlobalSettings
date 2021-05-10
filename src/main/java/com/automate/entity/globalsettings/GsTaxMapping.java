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
@Table(name="GS_TAX_MAPPING")
public class GsTaxMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="SERVICE_TYPE")
	private String serviceType;
	@Column(name="VENDOR_TYPE")
	private String vendorType;
	@Column(name="TAX_GROUP")
	private String taxGroup;
	@Column(name="SUPPLY_TYPE")
	private String supplyType;
	@Column(name="TAX_ACCOUNT")
	private String taxAccount;
	@Column(name="TAX_SECTION")
	private String taxSection;
	@Column(name="TAX_RATE_PERC")
	private String taxRatePerc;
	@Column(name="EFFECTIVE_FROM")
	private String effectiveFrom;
	@Column(name="EFFECTIVE_TO")
	private String effectiveTo;
	

}
