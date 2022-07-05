package com.example.demo.repository;

import com.example.demo.Model.SavingsAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccRepository extends JpaRepository<SavingsAcc, Integer> {
    SavingsAcc findByAccountNo(int accountNo);
    SavingsAcc findTopByOrderBySaidDesc();
}
