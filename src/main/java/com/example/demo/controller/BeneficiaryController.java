package com.example.demo.controller;

import com.example.demo.Model.Beneficiary;
import com.example.demo.Services.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/beneficiary")
public class BeneficiaryController {

    @Autowired
    BeneficiaryService beneficiaryService;

    @GetMapping(value = "/all")
    public List<Beneficiary> getAll() {
        return beneficiaryService.getAll();
    }

    @PostMapping(value = "/save")
    public String addBeneficiary(@RequestBody Beneficiary beneficiary) {
        return beneficiaryService.addBeneficiary(beneficiary);
    }
}