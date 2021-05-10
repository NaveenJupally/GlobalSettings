package com.automate.entity.globalsettings;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="GS_VENDOR_DIRECTOR")
public class GsVendorDirector implements Serializable{
	
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="ID")
		private Long id;
		@Column(name="FIRST_NAME")
		private String dirFirstName;
		@Column(name="LAST_NAME")
		private String dirLastName;
		@Column(name="DESIGNATION")
		private String dirDesignation;
		@Column(name="CONTACT_NO")
		private String dirContactNo;
		@Column(name="EMAIL_ID")
		private String dirEmailId;
		@Column(name="PAN_NO")
		private String dirPanNo;
		@Column(name="ADDRESS")
		private String dirAddressLine;
		@Column(name="PINCODE")
		private String dirPinCode;
		
		



}
