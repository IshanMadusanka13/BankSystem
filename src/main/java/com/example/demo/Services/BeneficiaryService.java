package com.example.demo.Services;

import com.example.demo.Model.Beneficiary;
import com.example.demo.Model.Customer;
import com.example.demo.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BeneficiaryService {

    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    //Find All
    public List<Beneficiary> getAll(){
        return beneficiaryRepository.findAll();
    }

    //Search
    public Beneficiary searchBeneficiary(String nic) {
        Beneficiary b= beneficiaryRepository.findByNic(nic);
        if (b==null){
            throw new NoSuchElementException("No Beneficiary Found with the NIC : "+nic);
        }else{
            return b;
        }
    }

    //Insert
    public String addBeneficiary(Beneficiary beneficiary){
        Beneficiary b= beneficiaryRepository.findByNic(beneficiary.getNic());
        if (b==null){
            beneficiaryRepository.save(beneficiary);
            return "Beneficiary added Successfully";
        }else{
            throw new NoSuchElementException("Beneficiary found with the NIC : "+beneficiary.getNic());
        }
    }

    //Update
    public String updateBeneficiary(Beneficiary beneficiary, String nic){
        Beneficiary b= beneficiaryRepository.findByNic(nic);
        if (b==null){
            throw new NoSuchElementException("customer not found with the NIC : "+beneficiary.getNic());
        }else{
            b.setName(beneficiary.getName());
            b.setNic(beneficiary.getNic());
            b.setAddress(beneficiary.getAddress());
            b.setMobile(beneficiary.getMobile());
            b.setEmail(beneficiary.getEmail());
            b.setRelationship(beneficiary.getRelationship());
            beneficiaryRepository.save(b);
            return "Customer Updated Successfully";
        }
    }

    //Delete
    public String deleteBeneficiary(Beneficiary beneficiary, String nic){
        Beneficiary b= beneficiaryRepository.findByNic(nic);
        if (b==null){
            throw new NoSuchElementException("customer not found with the NIC : "+beneficiary.getNic());
        }else{
            beneficiaryRepository.save(b);
            return "Customer Deleted Successfully";
        }
    }
}
