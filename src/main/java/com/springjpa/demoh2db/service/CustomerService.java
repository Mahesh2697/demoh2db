package com.springjpa.demoh2db.service;

import com.springjpa.demoh2db.entity.CustomerEntity;
import com.springjpa.demoh2db.model.CustomerModel;
import com.springjpa.demoh2db.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private List<CustomerModel> customerModelList ;





    public List<CustomerModel > getAllCustomers(){

        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerEntities.stream().map(p-> getAll(p)).collect(Collectors.toList());

    }

    public CustomerModel getCustomerById(Integer id) {
        Optional<CustomerEntity> customers = customerRepository.findById(id);
        if(customers.isPresent()){
            return getAll(customers.get());

        }
        return null;

    }
    public void delete(Integer id) {

        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(id);

        if (optionalCustomerEntity.isPresent()) {
            customerRepository.delete(optionalCustomerEntity.get());
        }

        //optionalCustomerEntity.ifPresent(customerEntity -> customerRepository.delete(customerEntity));
    }
    public CustomerModel update(int id, CustomerModel customerModel) {
        boolean flag=false;
        for(CustomerModel c:  customerModelList){
            if(c.getId()==id){
                System.out.println("equal");
                System.out.println(c.getId());
                c.setName(customerModel.getName());
                c.setEmail(customerModel.getEmail());
                c.setDesignation(customerModel.getDesignation());
                flag=true;
            }
        }
        if(flag=false){
            System.out.println("not equal");
            customerModelList.add(customerModel);
        }
        return customerModelList.stream().filter(p-> p.getId()==id).findFirst().get();

    }


    public String updateEmail(String email) {
        CustomerEntity customer = customerRepository.findByEmail("ram@gmail.com");
        customer.setEmail("dhev@gmail.com");
        customerRepository.save(customer);
        return "Hello";


    }

    public CustomerModel getCustomerByName(String name) {
        Optional<CustomerEntity> customers = customerRepository.findByName(name);

        if(customers.isPresent()){
            return getAll(customers.get());

        }
        return null;

    }




    public CustomerModel getByIdAndName(int id, String name) {
        Optional<CustomerEntity> customers = customerRepository.getByIdAndName(id, name);
        if(customers.isPresent()){
            return getAll(customers.get());

        }
        return null;

    }



    public void addCustomers(CustomerModel newCustomer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(newCustomer.getId());
        customerEntity.setName(newCustomer.getName());
        customerEntity.setEmail(newCustomer.getEmail());
        customerEntity.setDesignation(newCustomer.getDesignation());


        customerRepository.save(customerEntity);
    }



    private CustomerModel getAll(CustomerEntity  customerEntity) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customerEntity.getId());
        customerModel.setName(customerEntity.getName());
        customerModel.setEmail(customerEntity.getEmail());
        customerModel.setDesignation(customerEntity.getDesignation());





        return customerModel;


    }





}
