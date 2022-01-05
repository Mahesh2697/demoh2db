package com.springjpa.demoh2db.controller;

import com.springjpa.demoh2db.model.CustomerModel;
import com.springjpa.demoh2db.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add-customers")
    public void add(@Valid @RequestBody CustomerModel  newCustomer) {
        customerService.addCustomers(newCustomer);
    }
    @GetMapping("/get-all-customers")
    public List<CustomerModel> getAll(){return customerService.getAllCustomers();}

    @GetMapping("/getById/{id}")
    public CustomerModel getById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/getByName/{name}")
    public CustomerModel getByName(@PathVariable String name) {
        return customerService.getCustomerByName(name);
    }

    @PutMapping("/update-mail/{email}")
    public String email(@PathVariable String email) {
        return customerService.updateEmail(email);
    }


    @GetMapping("/getByIdAndName/{id}/{name}")
    public CustomerModel getByIdAndName(@PathVariable  int id, @PathVariable String name) {
        return customerService.getByIdAndName(id, name);
    }
    @PutMapping("/{id}")
    public CustomerModel updateCustomer(@PathVariable int id, @RequestBody CustomerModel customerModel){
        return customerService.update(id, customerModel);
    }

    @DeleteMapping("/delete-customer/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
    }




}
