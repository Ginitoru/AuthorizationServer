package com.iordache.web;

import com.iordache.security.config.AuthorizationServerConfig;
import com.iordache.security.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//vezi ca trebuie sa adaugi pt fiecare modul dependintele de la celelate module ca altfel face figuri si nu merge
@SpringBootApplication
@EntityScan(basePackages = {"com.iordache.*"})
@ComponentScan(basePackages = {"com.iordache.persistence.*", "com.iordache.web.*"}) //pt ca nu scaneaza automat modulele de componente
@Import({WebSecurityConfig.class, AuthorizationServerConfig.class}) //ca sa le vad mai bine
@EnableJpaRepositories("com.iordache.persistence.repositories") //ca sa vada interfata JpaRepository altfel nu o vede -> chiar daca am @ComponentScan
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);

    }

}
