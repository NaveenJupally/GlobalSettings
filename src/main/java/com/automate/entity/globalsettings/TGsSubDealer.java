package com.automate.entity.globalsettings;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.automate.model.globalsettings.AddressModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_gs_subdealer")
public class TGsSubDealer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sub_dealer_id")
	private int gsBusinessUnitId;

	@Column(name="sub_dealer_name")
	private String subDealerName;
	@Column(name="sub_dealer_code")
	private String subDealerCode;
	
	@OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address addresses;
}
