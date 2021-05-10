package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the t_gs_organaization database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_gs_organaization")
@NamedQuery(name = "TGsOrganaization.findAll", query = "SELECT t FROM TGsOrganaization t")
public class TGsOrganaization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parent_org_id")
	private int parentOrgId;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "org_desc")
	private String orgDesc;

	@Column(name = "org_logo")
	private String orgLogo;

	@Column(name = "org_name")
	private String orgName;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "status")
	private String status;

	// bi-directional many-to-one association to Address
	//@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	//private List<Address> addresses;

	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address addresses;

	
	// bi-directional one-to-one association to TGsContactDetail
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "contact_id")
	private TGsContactDetail TGsContactDetail;
	
//	@OneToMany(targetEntity = TGsAssetNature.class,cascade = CascadeType.ALL)
//	private TGsAssetNature tGsAssetNature;



}