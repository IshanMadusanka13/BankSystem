package com.example.demo.Services;

import com.example.demo.DTO.FixedDepositDTO;
import com.example.demo.DTO.SavingsAccDTO;
import com.example.demo.Model.FixedDeposit;
import com.example.demo.Model.SavingsAcc;
import com.example.demo.repository.FixedDepositRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Log4j2
@Service
@Transactional
public class FixedDepositService {

    private ModelMapper modelMapper;
    private FixedDepositRepository fixedDepositRepository;

    @Autowired
    public FixedDepositService(ModelMapper modelMapper, FixedDepositRepository fixedDepositRepository) {
        this.modelMapper = modelMapper;
        this.fixedDepositRepository = fixedDepositRepository;
    }

    //Search
    public FixedDeposit searchFixedAcc(int accNo) {
        FixedDeposit fd= fixedDepositRepository.findByAccountNo(accNo);
        if (fd==null){
            log.error("No Account found with the given Number");
            throw new NoSuchElementException("No Account Found with the given Number : "+accNo);
        }else{
            log.info("Searched for a Fixed Deposit");
            return modelMapper.map(fd,FixedDeposit.class);
        }
    }


    //Insert
    public String createFixedAcc(FixedDepositDTO fixedDepositDTO){
        fixedDepositDTO.setAccountNo(generateAccNo());
        fixedDepositRepository.save(modelMapper.map(fixedDepositDTO, FixedDeposit.class));
        return "Fixed Account Created Successfully";
    }


    //Update
    public String updateFixedAcc(FixedDepositDTO fixedDepositDTO, int accNo){
        try {
            FixedDepositDTO fd= modelMapper.map(fixedDepositRepository.findByAccountNo(accNo),FixedDepositDTO.class);
            fd.setBalance(fixedDepositDTO.getBalance());
            fd.setInterestRate(fixedDepositDTO.getInterestRate());
            fixedDepositRepository.save(modelMapper.map(fd,FixedDeposit.class));
            log.info("Updated Fixed Account Details");
            return "Fixed Account Updated Successfully";
        }catch (IllegalArgumentException e){
            log.error("Tried to update a non-existing Account");
            throw new NoSuchElementException("Account not found with the given Number : "+fixedDepositDTO.getAccountNo());
        }
    }


    //Delete
    public String deleteFixedAcc(FixedDepositDTO fixedDepositDTO, int accNo){
        try {
            FixedDepositDTO s= modelMapper.map(fixedDepositRepository.findByAccountNo(accNo),FixedDepositDTO.class);
            fixedDepositRepository.delete(modelMapper.map(s,FixedDeposit.class));
            log.info("Deleted Fixed Account");
            return " Fixed Account Deleted Successfully";
        }catch (IllegalArgumentException e){
            log.error("Tried to delete a non-existing Account");
            throw new NoSuchElementException("Account not found with the given Number : "+fixedDepositDTO.getAccountNo());
        }
    }



    public int generateAccNo(){
        FixedDeposit fd=fixedDepositRepository.findTopByOrderByFidDesc();
        int accNo = 10000000;
        if (fd==null){
            return accNo;
        }else{
            return fd.getAccountNo()+1;
        }
    }
}
