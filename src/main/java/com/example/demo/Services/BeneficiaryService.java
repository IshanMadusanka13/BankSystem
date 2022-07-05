package com.example.demo.Services;

import com.example.demo.DTO.BeneficiaryDTO;
import com.example.demo.DTO.CustomerDTO;
import com.example.demo.Enumset.Status;
import com.example.demo.Model.Beneficiary;
import com.example.demo.Model.Customer;
import com.example.demo.repository.BeneficiaryRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Transactional
@Service
public class BeneficiaryService {

    private ModelMapper modelMapper;
    private BeneficiaryRepository beneficiaryRepository;

    public BeneficiaryService(ModelMapper modelMapper, BeneficiaryRepository beneficiaryRepository) {
        this.modelMapper = modelMapper;
        this.beneficiaryRepository = beneficiaryRepository;
    }

    //Find all
    public List<BeneficiaryDTO> getAll(){
        List<Beneficiary> beneficiaries=beneficiaryRepository.findAll();
        return modelMapper.map(beneficiaries,new TypeToken<List<BeneficiaryDTO>>(){}.getType());
    }

    //Search
    public Beneficiary searchBeneficiary(String nic) {
        Beneficiary b= beneficiaryRepository.findByNic(nic);
        if (b==null){
            log.error("Searched for a non-existing Beneficiary");
            throw new NoSuchElementException("No Beneficiary Found with the NIC : "+nic);
        }else{
            log.info("Searched for a Beneficiary");
            return modelMapper.map(b,Beneficiary.class);
        }
    }

    //Insert
    public String addBeneficiary(BeneficiaryDTO beneficiaryDTO){
        Beneficiary b=beneficiaryRepository.findByNic(beneficiaryDTO.getNic());
        if (b==null){
            beneficiaryRepository.save(modelMapper.map(beneficiaryDTO,Beneficiary.class));
            log.info("Added new Beneficiary");
            return "Beneficiary added Successfully";
        }else {
            log.error("Inserted an already existing Beneficiary");
            throw new NoSuchElementException("Beneficiary found with the NIC : "+beneficiaryDTO.getNic());
        }
    }

    //Update
    public String updateBeneficiary(BeneficiaryDTO beneficiaryDTO, String nic){
        try {
            BeneficiaryDTO b= modelMapper.map(beneficiaryRepository.findByNic(nic),BeneficiaryDTO.class);
                b.setName(beneficiaryDTO.getName());
                b.setAddress(beneficiaryDTO.getAddress());
                b.setEmail(beneficiaryDTO.getEmail());
                b.setMobile(beneficiaryDTO.getMobile());
                b.setRelationship(beneficiaryDTO.getRelationship());
                beneficiaryRepository.save(modelMapper.map(b,Beneficiary.class));
                log.info("Updated a Beneficiary");
                return "Beneficiary Updated Successfully";
        }catch (IllegalArgumentException E){
            log.error("Tried to update a non-existing Beneficiary");
            throw new NoSuchElementException("Beneficiary not found with the NIC : "+beneficiaryDTO.getNic());
        }
    }

    //Delete
    public String deleteBeneficiary(BeneficiaryDTO beneficiaryDTO, String nic){
        try {
            BeneficiaryDTO b= modelMapper.map(beneficiaryRepository.findByNic(nic),BeneficiaryDTO.class);
            beneficiaryRepository.delete(modelMapper.map(b,Beneficiary.class));
            log.info("Beneficiary Deleted");
            return "Beneficiary Deleted Successfully";
        }catch (IllegalArgumentException e){
            log.error("Tried to delete a non-existing Beneficiary");
            throw new NoSuchElementException("customer not found with the id : "+beneficiaryDTO.getNic());
        }
    }

}
