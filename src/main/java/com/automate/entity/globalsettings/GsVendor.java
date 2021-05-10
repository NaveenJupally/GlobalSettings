package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author sruja
 *
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="GS_VENDOR_MASTER")
public class GsVendor implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vendorId;
	
	/*@Column(unique = true, updatable = false)
	protected UUID uuid;*/
	@Column(name="ORGANIZATION")
	private String organization;
	@Column(name="COMPANY_NAME")
	private String companyName;
	@Column(name="COMPANY_WEBSITE")
	private String companyWebSite;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="DESIGNATION")
	private String designation;
	@Column(name="CONTACT")
	private String contactNo;
	@Column(name="EMAIL")
	private String emailId;
	@Column(name="COMPANY_PAN_NO")
	private String companyPANNo;
	@Column(name="GST_NO")
	private String gstNo;
	@Column(name="SERVICE_TAX")
	private String serviceTax;
	@Column(name="TAN_NO")
	private String tanNo;
	@Column(name="STATUS_OF_PERSON")
	private String statusOfPerson;
	@Column(name="REGISTRATION_NO")
	private String registrationNo;
	@Column(name="REGISTERING_AUTHORITY")
	private String nameOfRegAuthority;
	@Column(name="REGISTRATION_DATE")
	private String dateOfRegistration;
	@Column(name="PLACE_OF_REGISTRATION")
	private String placeOfRegistration;
	@Column(name="EXPERIENCE")
	private String experience;
	@Column(name="TURNOVER")
	private String turnOver;
	@Column(name="BANK_AC_NO")
	private String accountNo;
	@Column(name="BANK_NAME")
	private String bankName;
	@Column(name="BANK_IFSC_CODE")
	private String ifscCode;
	@Column(name="BANK_BRANCH")
	private String branchName;
	@Column(name="BANK_ADDRESS")
	private String bankAddress;
	@Column(name="ATTACHEMNT")
	private String attachment;
	@Column(name = "created_at")
	private Timestamp createdAt;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	@Column(name = "update_by")
	private String updatedBy;

	
	@OneToMany(targetEntity = GsVendorDirector.class,fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="VENDOR_ID")
	private List<GsVendorDirector> directors;
	
	@OneToOne
	@JoinColumn(name="parent_org_id")
	private TGsOrganaization TGsOrganaization;
	
	@OneToOne
	@JoinColumn(name="gs_vertical_id")
	private TGsVertical tGsVertical;
	
	
	@OneToOne
	@JoinColumn(name="gs_business_unit_id")
	private TGsBusinessUnit tGsBusinessUnit;

	
	
}