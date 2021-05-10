package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


/**
 * The persistent class for the t_gs_tax_account database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_gs_tax_account")
@NamedQuery(name="TGsTaxAccount.findAll", query="SELECT t FROM TGsTaxAccount t")
public class TGsTaxAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gs_tax_account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gsTaxAccountId;

	@Column(name="account_type")
	private String accountType;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="tax_account")
	private String taxAccount;

	@Column(name="tax_account_name")
	private String taxAccountName;

	@Column(name="tax_group")
	private String taxGroup;

	@Column(name="tax_nature")
	private String taxNature;

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