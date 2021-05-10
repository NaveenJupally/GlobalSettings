package com.automate.entity.globalsettings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="GS_TAX_SECTION")
public class GsTaxSection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="TAX_SECTION_NAME")
	private String taxSectionName;
	@Column(name="STATUS")
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
