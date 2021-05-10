package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_tax_section database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_gs_tax_section")
@NamedQuery(name="TGsTaxSection.findAll", query="SELECT t FROM TGsTaxSection t")
public class TGsTaxSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_tax_section_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gsTaxSectionId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="section_name")
	private String sectionName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

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