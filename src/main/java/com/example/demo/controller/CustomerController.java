package com.example.demo.controller;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.Model.Customer;
import com.example.demo.Services.CustomerService;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value="/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/all")
    public List<CustomerDTO> getAll(){return customerService.getAll();}


    @GetMapping(value = "/search/{nic}")
    public Customer getByNic(@PathVariable String nic) {return customerService.searchCustomer(nic);}


    @PostMapping(value = "/save")
    public String addCustomer(@RequestBody CustomerDTO customerDTO){return customerService.addCustomer(customerDTO);}


    @PutMapping(value = "/update/{nic}")
    public String updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable String nic){return customerService.updateCustomer(customerDTO,nic);}


    @DeleteMapping(value = "/delete/{nic}")
    public String deleteCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable String nic){return customerService.deleteCustomer(customerDTO,nic);}

}

