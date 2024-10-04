package com.universitymanagementserver.server;

import com.universitymanagementserver.server.filers.ProtectedFilterRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<ProtectedFilterRoute> filterRegistrationBean() {
        FilterRegistrationBean<ProtectedFilterRoute> filterRegistrationBean = new FilterRegistrationBean<>();
        ProtectedFilterRoute protectedFilterRoute = new ProtectedFilterRoute();
        filterRegistrationBean.setFilter(protectedFilterRoute);
        filterRegistrationBean.addUrlPatterns("/api/v1/protected/*");
        return filterRegistrationBean;
    }

}
