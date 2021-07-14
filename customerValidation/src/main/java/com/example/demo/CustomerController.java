package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


 
@Controller
public class CustomerController {
 
    @Autowired
    CustomerService customerService;
    @Autowired
    customerRepo repo;
 

    @GetMapping("getCustomers")
    @CrossOrigin
    public ResponseEntity<Collection<CustomerDto>> getCustomer() {
       System.out.println("hellooo");
       Collection<CustomerDto> listOfusers= customerService.getCustomer();
        return new ResponseEntity<>(listOfusers,HttpStatus.OK);
    }
 
    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET, headers = "Accept=application/json")
    @CrossOrigin
    public String getAllCustomers(Model model) {
 
        List<Customer> listOfCustomers = customerService.getAllCustomers();
        model.addAttribute("customer", new Customer());
        model.addAttribute("listOfCustomers", listOfCustomers);
        return "customerDetails";
    }
 
    @RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
    public String goToHomePage() {
        return "redirect:/getAllCustomers";
    }
 
    @RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomer(id);
    }
 
    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST, headers = "Accept=application/json")
    @CrossOrigin
    public String addCustomer(@ModelAttribute("customer") Customer customer) {  
        if(customer.getId()==0)
        {
            customerService.addCustomer(customer);
        }
        else
        {   
            customerService.updateCustomer(customer);
        }
 
        return "redirect:/getAllCustomers";
    }
 
    @RequestMapping(value = "/updateCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String updateCustomer(@PathVariable("id") int id,Model model) {
        model.addAttribute("customer", this.customerService.getCustomer(id));
        model.addAttribute("listOfCustomers", this.customerService.getAllCustomers());
        return "customerDetails";
    }
 
    @RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String deleteCustomer(@PathVariable("id") int id) {
        customerService.deleteCustomer(id);
        return "redirect:/getAllCustomers";
 
    }   
    
    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<CustomerDto> register(@RequestBody Customer customer) { 
 
    	customerService.addCustomer(customer);
        return new ResponseEntity(customer,HttpStatus.OK);
    }
}