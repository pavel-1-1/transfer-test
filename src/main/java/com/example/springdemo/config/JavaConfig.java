package com.example.springdemo.config;

import com.example.springdemo.repository.Confirmation;
import com.example.springdemo.repository.Repository;
import com.example.springdemo.service.Service;
import com.example.springdemo.userCard.CardTransfer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class JavaConfig {
    @Bean
    public Service service() {
        return new Service(repository(), confirmation());
    }

    @Bean
    public Repository repository() {
        return new Repository();
    }

    @Bean
    @SessionScope
    public Confirmation confirmation() {
        return new Confirmation();
    }

    @Bean
    @SessionScope
    public CardTransfer cardTransfer() {
        return new CardTransfer();
    }
}
