package com.example.demo.account.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity(name="accounts")
@Table(name="accounts")
@Getter @Setter
public class Account {
    
    @Column(name="username")
    private String username;
    
    @Id
    @Column(name="account_number")
    private String accountNumber;
    
    @Column(name="bank")
    private String bank;
    
    @Column(name="balance")
    private float balance;
    
    @Column(name="currency")
    private String currency;
    
    @Column(name="account_name")
    private String accountName;    
        
    public Account() {        
    }
}
