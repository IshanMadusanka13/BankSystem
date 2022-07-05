package com.example.demo.Services;

import com.example.demo.DTO.SavingsAccDTO;
import com.example.demo.Model.SavingsAcc;
import com.example.demo.repository.SavingsAccRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Log4j2
@Service
@Transactional
public class SavingsAccService {

    private ModelMapper modelMapper;
    private SavingsAccRepository savingsAccRepository;

    @Autowired
    public SavingsAccService(ModelMapper modelMapper, SavingsAccRepository savingsAccRepository) {
        this.modelMapper = modelMapper;
        this.savingsAccRepository = savingsAccRepository;
    }

    //Search
    public SavingsAcc searchSavingsAcc(int accNo) {
        SavingsAcc s= savingsAccRepository.findByAccountNo(accNo);
        if (s==null){
            log.error("No Account found with the given Number");
            throw new NoSuchElementException("No Account Found with the given Number : "+accNo);
        }else{
            log.info("Searched for a Savings Account");
            return modelMapper.map(s,SavingsAcc.class);
        }
    }


    //Insert
    public String createSavingsAcc(SavingsAccDTO savingsAccDTO){
        savingsAccDTO.setAccountNo(generateAccNo());
        savingsAccRepository.save(modelMapper.map(savingsAccDTO, SavingsAcc.class));
        return "Savings Account Created Successfully";
    }


    //Update
    public String updateSavingsAcc(SavingsAccDTO savingsAccDTO, int accNo){
        try {
            SavingsAccDTO s= modelMapper.map(savingsAccRepository.findByAccountNo(accNo),SavingsAccDTO.class);
            s.setBalance(savingsAccDTO.getBalance());
            s.setInterestRate(savingsAccDTO.getInterestRate());
            savingsAccRepository.save(modelMapper.map(s,SavingsAcc.class));
            log.info("Updated Savings Account Details");
            return "Savings Account Updated Successfully";
        }catch (IllegalArgumentException e){
            log.error("Tried to update a non-existing Account");
            throw new NoSuchElementException("Account not found with the given Number : "+savingsAccDTO.getAccountNo());
        }
    }


    //Delete
    public String deleteSavingsAcc(SavingsAccDTO savingsAccDTO, int accNo){
        try {
            SavingsAccDTO s= modelMapper.map(savingsAccRepository.findByAccountNo(accNo),SavingsAccDTO.class);
            savingsAccRepository.delete(modelMapper.map(s,SavingsAcc.class));
            log.info("Deleted Savings Account");
            return " Savings Account Deleted Successfully";
        }catch (IllegalArgumentException e){
            log.error("Tried to delete a non-existing Account");
            throw new NoSuchElementException("Account not found with the given Number : "+savingsAccDTO.getAccountNo());
        }
    }

    public int generateAccNo(){
        SavingsAcc sl=savingsAccRepository.findTopByOrderBySaidDesc();
        int accNo = 10000000;
        if (sl==null){
            return accNo;
        }else{
            return sl.getAccountNo()+1;
        }
    }
}
