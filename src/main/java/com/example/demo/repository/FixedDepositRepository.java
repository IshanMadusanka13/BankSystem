package com.example.demo.repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.FixedDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDeposit,Integer> {
    FixedDeposit findByAccountNo(int accNo);
    FixedDeposit findTopByOrderByFidDesc();
}
