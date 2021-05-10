package com.automate.util;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author srujan
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Vendor {

	private String organization;
	private String companyName;
	private String companyWebSite;
	private String firstName;
	private String lastName;
	private String designation;
	private String contactNo;
	private String emailId;
	private String companyPANNo;
	private String gstNo;
	private String serviceTax;
	private String tanNo;
	private String statusOfPerson;
	private String registrationNo;
	private String nameOfRegAuthority;
	private String dateOfRegistration;
	private String placeOfRegistration;
	private String experience;
	private String turnOver;
	private String accountNo;
	private String bankName;
	private String ifscCode;
	private String branchName;
	private String bankAddress;
	private String attachment;
	private List<Director> directors;
}
