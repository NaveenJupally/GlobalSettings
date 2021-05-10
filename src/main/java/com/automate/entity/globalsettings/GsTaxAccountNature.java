package com.automate.entity.globalsettings;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="GS_TAX_ACCOUNT_NATURE")
public class GsTaxAccountNature {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="TAX_NATURE")
	private String taxNature;
	@Column(name="TAX_GROUP")
	private String taxGroup;
	@Column(name="TAX_ACCOUNT")
	private String taxAccount;
	@Column(name="TAX_ACCOUNT_TYPE")
	private String taxAccountType;
	@Column(name="STATUS")
	private String status;

}
