package com.example.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "one_entity")
public class OneEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "one")
	private String one;
	
	@OneToMany(mappedBy = "oneEntity", cascade = CascadeType.ALL)
	private List<TwoEntity> twoEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public List<TwoEntity> getTwoEntity() {
		return twoEntity;
	}

	public void setTwoEntity(List<TwoEntity> twoEntity) {
		this.twoEntity = twoEntity;
	}
}
