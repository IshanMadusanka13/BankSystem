package com.example.demo.controller;

import com.example.demo.DTO.BeneficiaryDTO;
import com.example.demo.DTO.CustomerDTO;
import com.example.demo.Model.Beneficiary;
import com.example.demo.Model.Customer;
import com.example.demo.Services.BeneficiaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/beneficiary")
public class BeneficiaryController {

    private BeneficiaryService beneficiaryService;

    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @GetMapping(value = "/all")
    public List<BeneficiaryDTO> getAll(){return beneficiaryService.getAll();}

    @GetMapping(value = "/search/{nic}")
    public Beneficiary getByNic(@PathVariable String nic) {return beneficiaryService.searchBeneficiary(nic);}

    @PostMapping(value = "/save")
    public String addBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO){return beneficiaryService.addBeneficiary(beneficiaryDTO);}

    @PutMapping(value = "/update/{nic}")
    public String updateBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO, @PathVariable String nic){return beneficiaryService.updateBeneficiary(beneficiaryDTO,nic);}

    @DeleteMapping(value = "/delete/{nic}")
    public String deleteBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO, @PathVariable String nic){return beneficiaryService.deleteBeneficiary(beneficiaryDTO,nic);}
}
