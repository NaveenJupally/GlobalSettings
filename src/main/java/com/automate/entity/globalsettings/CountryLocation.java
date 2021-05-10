package com.automate.entity.globalsettings;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "country_location")
public class CountryLocation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "country_location_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int countryLocationId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id")
	private TGsCountry TGsCountry;

	private String city;

	private String state;

	private String location;

	private String mandal;

	private String pincode;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCountryLocationId() {
		return countryLocationId;
	}

	public void setCountryLocationId(int countryLocationId) {
		this.countryLocationId = countryLocationId;
	}

	public TGsCountry getTGsCountry() {
		return TGsCountry;
	}

	public void setTGsCountry(TGsCountry tGsCountry) {
		TGsCountry = tGsCountry;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
