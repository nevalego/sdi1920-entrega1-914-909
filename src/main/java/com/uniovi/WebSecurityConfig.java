package com.uniovi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable().authorizeRequests()
		.antMatchers("/css/**", "/img/**", "/script/**", "/", "/signup",
			"/login/**")
		.permitAll().antMatchers("/publication/add")
		.hasAuthority("ROLE_STANDARD").antMatchers("/publication/list")
		.hasAnyAuthority("ROLE_STANDARD", "ROLE_ADMIN")
		.antMatchers("/publication/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/invitation/list")
		.hasAnyAuthority("ROLE_STANDARD", "ROLE_ADMIN")
		.antMatchers("/invitation/add/**")
		.hasAnyAuthority("ROLE_STANDARD", "ROLE_ADMIN")
		.antMatchers("/invitation/accept/**")
		.hasAnyAuthority("ROLE_STANDARD", "ROLE_ADMIN")
		.antMatchers("/invitation/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/friendship/list")
		.hasAnyAuthority("ROLE_STANDARD", "ROLE_ADMIN")
		.antMatchers("/friendship/details/**")
		.hasAnyAuthority("ROLE_STANDARD", "ROLE_ADMIN")
		.antMatchers("/friendship/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/friendship/**").hasAuthority("ROLE_ADMIN")
		.antMatchers("/user/list")
		.hasAnyAuthority("ROLE_STANDARD", "ROLE_ADMIN")
		.antMatchers("/user/**").hasAnyAuthority("ROLE_ADMIN")
		.anyRequest().authenticated().and().formLogin()
		.loginPage("/login").permitAll().defaultSuccessUrl("/home")
		.and().logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
	    throws Exception {
	auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
	return new SpringSecurityDialect();
    }
}
