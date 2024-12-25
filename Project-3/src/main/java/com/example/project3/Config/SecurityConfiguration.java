package com.example.project3.Config;

import com.example.project3.Service.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final MyUserDetailService myUserDetailService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(myUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/bank-system/customer/register","api/v1/bank-system/employee/register").permitAll()
                .requestMatchers("/api/v1/bank-system/customer/update" ,
                                 "/api/v1/bank-system/customer/delete",
                                 "/api/v1/bank-system/customer/my-info",
                                 "/api/v1/bank-system/account/get",
                                 "/api/v1/bank-system/account/create",
                                 "/api/v1/bank-system/account/delete/",
                                 "/api/v1/bank-system/account/withdraw/{account_id}/{amount}",
                                 "/api/v1/bank-system/account/deposit/{account_id}/{amount}" ,
                                 "/api/v1/bank-system/account/transfer-funds/{account1_id}/{account2_id}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/bank-system/employee/update" ,
                                 "/api/v1/bank-system/employee/delete",
                                 "/api/v1/bank-system/employee/my-info").hasAuthority("EMPLOYEE")
                .requestMatchers("/api/v1/bank-system/account/account-detail/{account_id}").hasAnyAuthority("CUSTOMER","EMPLOYEE","ADMIN")
                .requestMatchers("/api/v1/bank-system/account/active-account/{account_id}",
                                 "/api/v1/bank-system/account/all-accounts",
                                 "/api/v1/bank-system/account/block-account/{account_id}").hasAnyAuthority("EMPLOYEE","ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }


}
