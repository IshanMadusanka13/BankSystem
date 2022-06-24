package com.example.demo.controller;

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

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/all")
    public List<Customer> getAll(){return customerService.getAll();}


    @GetMapping(value = "/search/{nic}")
    public Customer getByNic(@PathVariable String nic) {return customerService.searchCustomer(nic);}


    @PostMapping(value = "/save")
    public String addCustomer(@RequestBody Customer customer){return customerService.addCustomer(customer);}


    @PostMapping(value = "/update/{nic}")
    public String updateCustomer(@RequestBody Customer customer, @PathVariable String nic){return customerService.updateCustomer(customer,nic);}


    @PostMapping(value = "/delete/{nic}")
    public String deleteCustomer(@RequestBody Customer customer, @PathVariable String nic){return customerService.deleteCustomer(customer,nic);}
}
