package com.example.demo.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;



@Entity(name="users")
@Table(name="users")
@Getter @Setter
public class User {

    @Id
    @Column(name="username")
    private String username;
    
    @Column(name="password")
    private String password;
    
    @Column(name="enabled")
    private Integer enabled;
    
    @Column(name="email")
    private String email;
    
    @Transient
    private String repeatPassword;
    
    @Transient
    private String newPassword;
        
    public User() {
        
    }    
}
