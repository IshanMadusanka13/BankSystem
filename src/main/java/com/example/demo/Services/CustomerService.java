package com.example.demo.Services;

import com.example.demo.Model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    //Find all
    public List<Customer> getAll(){return customerRepository.findAll();}

    //Search
    public Customer searchCustomer(String nic) {
        Customer c= customerRepository.findByNic(nic);
        if (c==null){
            throw new NoSuchElementException("No Customer Found with the NIC : "+nic);
        }else{
            return c;
        }
    }

    //Insert
    public String addCustomer(Customer customer){
        Customer c= customerRepository.findByNic(customer.getNic());
        if (c==null){
            customer.setStatus(1);
            customerRepository.save(customer);
            return "Customer added Successfully";
        }else{
            throw new NoSuchElementException("customer found with the NIC : "+customer.getNic());
        }
    }

    //update
    public String updateCustomer(Customer customer, String nic){
        Customer c= customerRepository.findByNic(nic);
        if (c==null){
            throw new NoSuchElementException("customer not found with the NIC : "+customer.getNic());
        }else{
            c.setName(customer.getName());
            c.setDob(customer.getDob());
            c.setAddress(customer.getAddress());
            c.setOccupation(customer.getOccupation());
            c.setEmail(customer.getEmail());
            c.setMobile(customer.getMobile());
            customerRepository.save(c);
            return "Customer Updated Successfully";
        }
    }

    //Delete
    public String deleteCustomer(Customer customer, String nic){
        Customer c= customerRepository.findByNic(nic);
        if (c==null){
            throw new NoSuchElementException("customer not found with the NIC : "+customer.getNic());
        }else{
            c.setStatus(0);
            customerRepository.save(c);
            return "Customer Deleted Successfully";
        }
    }

    /*
    @PostMapping(value = "/deletef/{nic}")
    public String deletefCustomer(@RequestBody Customer customer, @PathVariable String nic){
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
