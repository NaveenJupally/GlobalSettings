package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the t_gs_tax_mapping database table.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="t_gs_tax_mapping")
@NamedQuery(name="TGsTaxMapping.findAll", query="SELECT t FROM TGsTaxMapping t")
public class TGsTaxMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_tax_mapping_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gsTaxMappingId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="effective_from")
	private String effectiveFrom;

	@Column(name="effective_to")
	private String effectiveTo;

	@Column(name="service_type")
	private String serviceType;

	@Column(name="supply_type")
	private String supplyType;

	@Column(name="tax_account")
	private String taxAccount;

	@Column(name="tax_group")
	private String taxGroup;

	@Column(name="tax_rate")
	private String taxRate;

	@Column(name="tax_section")
	private String taxSection;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="vendor_type")
	private String vendorType;

	@Column(name="status")
	private String status;

	//bi-directional many-to-one association to TGsOrganaization
	@ManyToOne
	@JoinColumn(name="parent_org_id")
	private TGsOrganaization TGsOrganaization;

	@ManyToOne
	@JoinColumn(name="gs_vertical_id")
	private TGsVertical tGsVertical;
	
	
	@ManyToOne
	@JoinColumn(name="gs_business_unit_id")
	private TGsBusinessUnit tGsBusinessUnit;

	
}