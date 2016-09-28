package com.website.controller;

import com.website.model.Customer;
import com.website.model.CustomerPayment;
import com.website.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Created by tadasyan.
 */

@Controller
public class WebController {

    @Autowired
    CustomerService customerService;

//    @Autowired
//    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        }
        else{
            return "redirect:/test";
        }

    }

    @RequestMapping(value = "/payments", method = RequestMethod.GET)
    public String getPayments(Model model, @PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Customer customer = customerService.findByUsername(getPrincipal());
        Page<CustomerPayment> payments = customerService.getPaymentsByUsername(getPrincipal(), pageable);
        model.addAttribute("sort", String.format("%s,%s", pageable.getSort().iterator().next().getProperty(),pageable.getSort().iterator().next().getDirection().name()));
        model.addAttribute("payments", payments);
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

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";
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

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("user", getPrincipal());
        return "test";
    }


}
