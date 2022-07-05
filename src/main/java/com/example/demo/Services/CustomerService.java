package com.example.demo.Services;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.Enumset.Status;
import com.example.demo.Model.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Service
@Transactional
public class CustomerService {

    private ModelMapper modelMapper;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository=customerRepository;
        this.modelMapper=modelMapper;
    }

    //Find all
    public List<CustomerDTO> getAll(){
        List<Customer> customers=customerRepository.findAll();
        return modelMapper.map(customers,new TypeToken<List<CustomerDTO>>(){}.getType());
    }

    //Search
    public Customer searchCustomer(String nic) {
        Customer c= customerRepository.findByNic(nic);
        if (c==null){
            log.error("Searched for a non-existing customer");
            throw new NoSuchElementException("No Customer Found with the NIC : "+nic);
        }else{
            log.info("Searched for a customer");
            return modelMapper.map(c,Customer.class);
        }
    }

    //Insert
    public String addCustomer(CustomerDTO customerDTO){
        Customer c= customerRepository.findByNic(customerDTO.getNic());
        if (c==null){
            customerDTO.setStatus(Status.ACTIVE);
            customerRepository.save(modelMapper.map(customerDTO,Customer.class));
            log.info("Added new customer");
            return "Customer added Successfully";
        }else{
            log.error("Inserted an already existing customer");
            throw new NoSuchElementException("customer found with the NIC : "+customerDTO.getNic());
        }
    }

    //update
    public String updateCustomer(CustomerDTO customerDTO, String nic){
        try {
            CustomerDTO c= modelMapper.map(customerRepository.findByNic(nic),CustomerDTO.class);
            c.setName(customerDTO.getName());
            c.setDob(customerDTO.getDob());
            c.setAddress(customerDTO.getAddress());
            c.setOccupation(customerDTO.getOccupation());
            c.setEmail(customerDTO.getEmail());
            c.setMobile(customerDTO.getMobile());
            c.setStatus(Status.ACTIVE);
            customerRepository.save(modelMapper.map(c,Customer.class));
            log.info("Updated a customer");
            return "Customer Updated Successfully";
        }catch (IllegalArgumentException e){
            log.error("Tried to update a non-existing customer");
            throw new NoSuchElementException("customer not found with the NIC : "+customerDTO.getNic());
        }
    }

    //Delete
    public String deleteCustomer(CustomerDTO customerDTO, String nic){
        try {
            CustomerDTO c= modelMapper.map(customerRepository.findByNic(nic),CustomerDTO.class);
            c.setStatus(Status.INACTIVE);
            customerRepository.save(modelMapper.map(c,Customer.class));
            log.info("Deleted a customer");
            return "Customer Deleted Successfully";
        }catch (IllegalArgumentException e){
            log.error("Tried to delete a non-existing customer");
            throw new NoSuchElementException("customer not found with the NIC : "+customerDTO.getNic());
        }
    }

    /*
    public String deletefCustomer(Customer customer, String nic){
        Customer c= customerRepository.findByNic(nic);
        if (c==null){
            throw new NoSuchElementException("customer not found with the id : "+customer.getNic());
        }else{
            customerRepository.delete(c);
            return "Customer Deleted Succesfully";
        }
    }
*/

}
