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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.automate.model.globalsettings.CommunicationAddr;
import com.automate.model.globalsettings.CorrespondenceAddr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the t_gs_business_unit database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_gs_business_unit")
@NamedQuery(name="TGsBusinessUnit.findAll", query="SELECT t FROM TGsBusinessUnit t")
public class TGsBusinessUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="gs_business_unit_id")
	private int gsBusinessUnitId;

	@Column(name="business_unit_desc")
	private String businessUnitDesc;

	@Column(name="business_unit_logo")
	private String businessUnitLogo;

	@Column(name="business_unit_name")
	private String businessUnitName;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	

	@Column(name="google_location")
	private String googleLocation;

	@Column(name="oem_type")
	private String oemType;

	@Column(name="sub_dealer_code")
	private String subDealerCode;

	@Column(name="sub_dealer_name")
	private String subDealerName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="website_url")
	private String websiteUrl;

	@Column(name="status")
	private String status;
	
	


	//@OneToMany(targetEntity = Address.class,cascade=CascadeType.ALL)
	//private List<Address> addresses;
	

	@OneToMany(targetEntity = TGsSubDealer.class,cascade=CascadeType.ALL)
	@JoinColumn(name = "gs_business_unit_id")
	private List<TGsSubDealer> subDealerList;
	
	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address addresses;

	
	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "business_unit_address_id")
	private Address buAddresses;
	
	
	//bi-directional one-to-one association to TGsContactDetail
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="contact_id")
	private TGsContactDetail TGsContactDetail;


	@ManyToOne
	@JoinColumn(name="parent_org_id")
	TGsOrganaization tGsOrganaization;

	@ManyToOne
	@JoinColumn(name="gs_vertical_id")
	TGsVertical tGsVertical;
	
	
	

}