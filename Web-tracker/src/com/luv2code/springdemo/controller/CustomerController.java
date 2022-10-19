package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.Services.CustomerServices;
import com.luv2code.springdemo.dao.CustomerDao;
import com.luv2code.springdemo.entity.Customer;

@Controller()
@RequestMapping("/customer")
public class CustomerController {

	
	
	//inject customer services
	
	@Autowired
	private CustomerServices customerServices;
	
	@GetMapping("/list")
	//@PostMapping("/list")

	public String listCustomer(Model theModel) {
		
		//get customer from dao
		List<Customer>theCustomer = customerServices.getCustomer();
		//add customers to the model
		
		theModel.addAttribute("customers",theCustomer);
		return "list-customers";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind the date
		
		Customer thecustomer = new Customer();
		theModel.addAttribute("customer" ,thecustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer thecustomer ) {
		customerServices.saveCustomer(thecustomer);
		return "redirect:/customer/list";
	}
	@GetMapping("showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){
		
		// get the customer from our service
		Customer theCustomer = customerServices.getCustomer(theId);
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// send over to our form	
		return "customer-form";
	}
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId){
		
		// delete the customer
		customerServices.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	}

