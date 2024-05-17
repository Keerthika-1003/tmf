package com.tmf.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "settlementaccount")
public class SettlementAccount {

	@Id
    private Long id;
	private int accountNo;
	private String description;
	public SettlementAccount(Long id, int accountNo, String description) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.description = description;
	}
	public SettlementAccount() {
		super();
		// TODO Auto-generated constructor stub
	} 
    
    

}
