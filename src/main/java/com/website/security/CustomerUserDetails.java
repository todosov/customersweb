package com.website.security;

import com.website.model.Customer;
import com.website.model.Profile;
import com.website.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tadasyan.
 */

@Service("customUserDetailsService")
public class CustomerUserDetails implements UserDetailsService {

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String customerUsername) throws UsernameNotFoundException {
        Customer customer = customerService.findByUsername(customerUsername);
        if (customer != null){
            List<GrantedAuthority> authorities = new ArrayList<>();
            Set<Profile> profiles = customer.getProfiles();
            authorities.addAll(profiles.stream().map(
                    profile -> new SimpleGrantedAuthority(("ROLE_" + profile.getType()))).collect(Collectors.toList()));
            //authorities.add(new SimpleGrantedAuthority("ROLE_" + customer.getProfiles()));
            return new User(customer.getUsername(), customer.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("Username " + customerUsername + " not found");
    }
}
