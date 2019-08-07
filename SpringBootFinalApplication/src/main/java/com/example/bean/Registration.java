package com.example.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.example.dto.RegistrationDTO;

@Entity
@Table(name="registration")
public class Registration  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "regId")
	private Long regId;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pincode")
	private String pincode;
	
	@OneToOne(mappedBy = "registration", cascade = CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = User.class)
	private User user;

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {	
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public RegistrationDTO convertTo() {
		RegistrationDTO registrationDTO= new RegistrationDTO();
		registrationDTO.setAddress(address);
		registrationDTO.setPincode(pincode);
		registrationDTO.setUser(user);
		registrationDTO.setRegId(regId);
		return registrationDTO;
	}
}
