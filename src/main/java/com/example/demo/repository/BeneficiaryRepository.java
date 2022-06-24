package com.example.demo.repository;

import com.example.demo.Model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Integer> {
    Beneficiary findByNic(String nic);
}
