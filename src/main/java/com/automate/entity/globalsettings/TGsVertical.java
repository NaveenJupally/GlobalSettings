package com.automate.entity.globalsettings;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the t_gs_vertical database table.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_gs_vertical")
@NamedQuery(name="TGsVertical.findAll", query="SELECT t FROM TGsVertical t")
public class TGsVertical implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="gs_vertical_id")
	private int gsVerticalId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="org_desc")
	private String orgDesc;

	@Column(name="org_logo")
	private String orgLogo;

	@Column(name="org_name")
	private String orgName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="status")
	private String status;



	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address addresses;
	//@OneToMany(targetEntity = Address.class,cascade=CascadeType.ALL)
	//private List<Address> addresses;
	
	@ManyToOne
	@JoinColumn(name="parent_org_id")
	TGsOrganaization tGsOrganaization;
	
	//bi-directional one-to-one association to TGsContactDetail
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="contact_id")
	private TGsContactDetail TGsContactDetail;


}