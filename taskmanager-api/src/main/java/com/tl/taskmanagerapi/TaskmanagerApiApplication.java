package com.tl.taskmanagerapi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.tl.taskmanagerapi.configuration.CorsFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@SpringBootApplication
public class TaskmanagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerApiApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		registrationBean.setFilter(new CorsFilter());
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	} 
	
	@Configuration
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        public void configure(WebSecurity web) throws Exception {
             web.ignoring().antMatchers("/**");
        }
    }

	
}
