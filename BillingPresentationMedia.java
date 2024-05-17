package com.tmf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "BillingMedia")
public class BillingPresentationMedia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name") // Specify exact column definition
	private String name;
	@Column(name = "description") // Specify exact column definition
	private int description;

	public BillingPresentationMedia(Long id, String name, Integer description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public BillingPresentationMedia() {
		super();
		// TODO Auto-generated constructor stub
	}

}
