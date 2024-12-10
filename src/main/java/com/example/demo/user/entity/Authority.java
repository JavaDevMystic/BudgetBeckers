package com.example.demo.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity(name="authorities")
@Table(name="authorities")
@Getter @Setter
public class Authority {

    @Id
    @Column(name="username")
    private String username;
    
    @Column(name="authority")
    private String authority;

    public Authority() {
    }
}
