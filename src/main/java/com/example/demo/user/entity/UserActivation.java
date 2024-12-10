package com.example.demo.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

@Entity(name="users_activation")
@Table(name="users_activation")
@Getter @Setter
public class UserActivation {

    @Id
    @Column(name="username")
    private String username;
    
    @Column(name="activation_code")
    private String activationCode;
    
    @Column(name="expiration")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime expiration;

    public UserActivation() {
    }    
    
    public UserActivation(String username, String activationCode, LocalDateTime expiration) {
        this.username = username;
        this.activationCode = activationCode;
        this.expiration = expiration;
    }
    
}
