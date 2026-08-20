package com.pm.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.servlet.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("/register").permitAll().anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder(12);
   }
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService)
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        System.out.println(">>> MY AUTHENTICATION Prov CREATED");
        return daoAuthenticationProvider;
    }

}
   /*
   .
   .
   .
   .
   .

     http.csrf(customizer ->customizer.disable());
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

         return http.build();
       */


       /* Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer_customizer) {
                httpSecurityCsrfConfigurer_customizer.disable();
            }
        }
        http.csrf(csrfCustomizer) */


   /* @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("kivi")
                .password("k@123")
                .roles("ADMIN")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("kavi")
                .password("k@1234")
                .roles("User")
                .build();



        return new InMemoryUserDetailsManager(user1,user2);
    } */