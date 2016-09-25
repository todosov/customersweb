package com.website.controller;

import com.sun.javafx.sg.prism.NGShape;
import com.website.model.Customer;
import com.website.model.CustomerPayment;
import com.website.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * Created by tadasyan.
 */

@Controller
public class WebController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public String getPayments(Model model) {
        Customer customer = customerService.findByUsername(getPrincipal());
        model.addAttribute("payments", customer.getCustomerPayments()
                .stream()
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .collect(Collectors.toList()));
        model.addAttribute("customer", String.format("%s %s", customer.getFirstName(), customer.getLastName()));
        return "payments";
    }

    @RequestMapping(value = "/newpayment", method = RequestMethod.GET)
    public String ShowNewPayment(){
        return "newpayment";
    }

    @RequestMapping(value = "/newpayment", method = RequestMethod.POST)
    public String processNewPayment(@ModelAttribute CustomerPayment customerPayment){
        customerPayment.setDate(LocalDateTime.now());
        customerPayment.setCustomer(customerService.findByUsername(getPrincipal()));
        customerService.saveCustomerPayment(customerPayment);
        return "redirect:/payments";
    }

    private String getPrincipal(){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        }
        else{
            username = principal.toString();
        }
        return username;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("user", getPrincipal());
        return "test";
    }


}
