package com.example.demo.controller;

import com.example.demo.DTO.FixedDepositDTO;
import com.example.demo.DTO.SavingsAccDTO;
import com.example.demo.Model.FixedDeposit;
import com.example.demo.Model.SavingsAcc;
import com.example.demo.Services.FixedDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/fixeddeposit")
public class FixedDepositController {
    private FixedDepositService fixedDepositService;

    @Autowired
    public FixedDepositController(FixedDepositService fixedDepositService) {
        this.fixedDepositService = fixedDepositService;
    }

    @GetMapping(value = "/search/{accNo}")
    public FixedDeposit searchAccount(@PathVariable int accNo) {return fixedDepositService.searchFixedAcc(accNo);}

    @PostMapping(value = "/save")
    public String createAccount(@RequestBody FixedDepositDTO fixedDepositDTO){return fixedDepositService.createFixedAcc(fixedDepositDTO);}

    @PutMapping(value = "/update/{accNo}")
    public String updateAccount(@RequestBody FixedDepositDTO fixedDepositDTO, @PathVariable int accNo){return fixedDepositService.updateFixedAcc(fixedDepositDTO,accNo);}

    @DeleteMapping(value = "/delete/{accNo}")
    public String deleteAccount(@RequestBody FixedDepositDTO fixedDepositDTO, @PathVariable int accNo){return fixedDepositService.deleteFixedAcc(fixedDepositDTO,accNo);}
}
