package com.example.demo.controller;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.SavingsAccDTO;
import com.example.demo.Model.Customer;
import com.example.demo.Model.SavingsAcc;
import com.example.demo.Services.SavingsAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/savings")
public class SavingsAccController {

    private SavingsAccService savingsAccService;

    @Autowired
    public SavingsAccController(SavingsAccService savingsAccService) {
        this.savingsAccService = savingsAccService;
    }

    @GetMapping(value = "/search/{accNo}")
    public SavingsAcc searchAccount(@PathVariable int accNo) {return savingsAccService.searchSavingsAcc(accNo);}

    @PostMapping(value = "/save")
    public String createAccount(@RequestBody SavingsAccDTO savingsAccDTO){return savingsAccService.createSavingsAcc(savingsAccDTO);}

    @PutMapping(value = "/update/{accNo}")
    public String updateAccount(@RequestBody SavingsAccDTO savingsAccDTO, @PathVariable int accNo){return savingsAccService.updateSavingsAcc(savingsAccDTO,accNo);}

    @DeleteMapping(value = "/delete/{accNo}")
    public String deleteAccount(@RequestBody SavingsAccDTO savingsAccDTO, @PathVariable int accNo){return savingsAccService.deleteSavingsAcc(savingsAccDTO,accNo);}
}
