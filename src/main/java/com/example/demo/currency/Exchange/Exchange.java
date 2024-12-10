package com.example.demo.currency.Exchange;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;

@Getter @Setter
public class Exchange {

    private String base;
    
    private HashMap<String, Float> rates;
    
    private Date date; 
    
    public Exchange() {
        
    }
}
