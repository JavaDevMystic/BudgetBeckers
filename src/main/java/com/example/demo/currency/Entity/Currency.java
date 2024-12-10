package com.example.demo.currency.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;




@Entity(name="currency")
@Table(name="currency")
@Getter @Setter
public class Currency {
    
    @Id
    @Column(name="currency")
    private String currency;
    
    @Column(name="name")
    private String name;
        
    public Currency() {
        
    }
}
