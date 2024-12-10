package com.example.demo.transaction.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class TransactionSearch {

    String description;
    
    String fromAccount;
    
    String toAccount;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
    
    String currency;
    
    String isPlanned;
    
    public TransactionSearch() {
        
    }
}
