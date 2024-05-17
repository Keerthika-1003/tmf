package com.tmf.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmf.entity.BillingPresentationMedia;
@Repository
public interface BillingPresentationMediaRepo  extends JpaRepository<BillingPresentationMedia, Long> { 

}
