package com.Restdemo.RestDemo.dto;


public class Userdto {
    private String id;
    private  String name;
    private String email;

    public Userdto(String id,String name,String email) {
        this.name = name;
        this.id=id;
        this.email=email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id){
        this.id=id;
    }



}
