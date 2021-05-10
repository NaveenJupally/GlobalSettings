package com.automate.entity.globalsettings;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the address database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id")
	private int addrId;

	private String city;

	private String country;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="line_no1")
	private String lineNo1;

	@Column(name="line_no2")
	private String lineNo2;

	@Column(name="line_no3")
	private String lineNo3;
	
	
	@Column(name="c_checkbox")
	private String correspondenceAddCheckbox;
	@Column(name="c_line_no1")
	private String correspondenceAddLine1;
	@Column(name="c_line_no2")
	private String correspondenceAddLine2;
	@Column(name="c_line_no3")
	private String correspondenceAddLine3;

	private String location;

	private String mandal;

	private String pincode;

	private String state;

	private String type;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="updated_by")
	private String updatedBy;
	
	

	//bi-directional many-to-one association to TGsOrganaization
/*	@ManyToOne
	@JoinColumn(name="address_id" , insertable=false, updatable=false)
	private TGsOrganaization TGsOrganaization;

	//bi-directional many-to-one association to TGsVertical
	@ManyToOne
	@JoinColumn(name="address_id" , insertable=false, updatable=false)
	private TGsVertical TGsVertical;

	//bi-directional many-to-one association to TGsBusinessUnit
	@ManyToOne
	@JoinColumn(name="address_id" , insertable=false, updatable=false)
	private TGsBusinessUnit TGsBusinessUnit;
*/
	
}