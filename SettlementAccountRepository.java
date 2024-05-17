package com.tmf.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmf.entity.SettlementAccount;
@Repository
public interface SettlementAccountRepository extends JpaRepository<SettlementAccount, Long> {

}
