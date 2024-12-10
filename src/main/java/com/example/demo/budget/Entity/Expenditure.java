package com.example.demo.budget.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;



@Table(name="expenditures")
@Entity(name="expenditures")
@Getter @Setter
public class Expenditure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="budget_id")
    private int budgetId;
    
    @Column(name="amount")
    private float amount;
    
    @Column(name="description")
    private String description;

    @Transient
    private String edit;        
    
    public Expenditure() {
        
    }
    
    public Expenditure(int budgetId, String edit) {
        this.budgetId = budgetId;
        this.edit = edit;
    }
}
