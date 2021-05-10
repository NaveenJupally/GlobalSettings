package com.automate.entity.globalsettings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="GS_TAX_GROUP")
public class GsTaxGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="ORG_NAME")
	private String orgName;
	@Column(name="TAX_GROUP")
	private String taxGroup;
	@Column(name="STATUS")
	private String status;

}
