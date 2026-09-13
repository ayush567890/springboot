package com.Restdemo.RestDemo.entity;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false,unique=true)
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getname(){
        return name;
    }

    public void setname(String name){
        this.name=name;
    }

    public String getemail(){
        return email;
    }

    public void setemail(String email){
        this.email=email;
    }




}
