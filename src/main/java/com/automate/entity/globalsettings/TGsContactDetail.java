package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the t_gs_contact_details database table.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_gs_contact_details")
@NamedQuery(name="TGsContactDetail.findAll", query="SELECT t FROM TGsContactDetail t")
public class TGsContactDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contact_id")
	private int contactId;

	@Column(name="contact_number")
	private String contactNumber;

	@Column(name="google_location")
	private String googleLocation;

	@Column(name="gst_no")
	private String gstNo;

	@Column(name="pan_no")
	private String panNo;

	@Column(name="primary_contact_email_id")
	private String primaryContactEmailId;

	@Column(name="primary_contact_no")
	private String primaryContactNo;

	@Column(name="primary_contact_person")
	private String primaryContactPerson;

	@Column(name="secondary_contact_email_id1")
	private String secondaryContactEmailId1;

	@Column(name="secondary_contact_email_id2")
	private String secondaryContactEmailId2;

	@Column(name="secondary_contact_no1")
	private String secondaryContactNo1;

	@Column(name="secondary_contact_no2")
	private String secondaryContactNo2;

	@Column(name="secondary_contact_person1")
	private String secondaryContactPerson1;

	@Column(name="secondary_contact_person2")
	private String secondaryContactPerson2;

	@Column(name="web_site_url")
	private String webSiteUrl;

	//bi-directional one-to-one association to TGsOrganaization
	/*@OneToOne(mappedBy="TGsContactDetail")
	private TGsOrganaization TGsOrganaization;

	//bi-directional one-to-one association to TGsVertical
	@OneToOne(mappedBy="TGsContactDetail")
	private TGsVertical TGsVertical;

	//bi-directional one-to-one association to TGsBusinessUnit
	@OneToOne(mappedBy="TGsContactDetail")
	private TGsBusinessUnit TGsBusinessUnit;
*/
	
}