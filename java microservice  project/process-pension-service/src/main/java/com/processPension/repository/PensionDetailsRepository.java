package com.processPension.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.processPension.model.PensionAmountDetail;

@Repository
public interface PensionDetailsRepository extends JpaRepository<PensionAmountDetail, String>{

}
