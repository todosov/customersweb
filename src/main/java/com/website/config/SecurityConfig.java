package com.website.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

/**
 * Created by tadasyan.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetails;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test").hasRole("ADMIN")
                .antMatchers("/payments").hasRole("USER")
                .antMatchers("/**").permitAll()
                .and().formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/access_denied");
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
//
//    @Bean
//    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices(){
//        PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices = new PersistentTokenBasedRememberMeServices("remember-me", userDetails,  )
//    }
}
