package com.example.demo.transaction.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter @Setter
public class Transaction {

    @Id
    @Column(name="transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    
    @Column(name="username")
    private String username;
    
    @Column(name="account_number_from")
    private String accountNumberFrom;
    
    @Column(name="account_number_to")
    private String accountNumberTo;
    
    @Column(name="amount")
    private float amount;
    
    @Column(name="datetime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;
    
    @Column(name="description")
    private String description;
    
    @Column(name="expenditure_type")
    private String expenditureType;
    
    @Column(name="debt_id")
    private int debtId;
    
    @Column(name="currency")
    private String currency;
    
    @Column(name="type")
    private String type;
    
    @Transient
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    
    @Transient
    private String ifIsDebtRepayment;    
    
    public Transaction() {
        
    }
}
