package com.example.kontakty.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class Contact { // Klasa Contact w ktorej trzymane sa wszystkie informacje o kontakcie
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    private String lastName;

    private String email;

    private String password;

    private String category;

    private String subcategory;

    private int phoneNumber;

    private LocalDate birthDate;

    public Contact(){

    }
    public Contact(long id, String name, String lastName, String email, String password, String category,String subcategory, int phoneNumber, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.category = category;
        this.subcategory = subcategory;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCategory(){
        return category;
    }

    public String getSubcategory(){
        return subcategory;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }
    public LocalDate getBirthDate(){
        return birthDate;
    }

    @Override
    public String toString() { //toString jest zapisany w takiej postaci aby nie gryzl sie ze skladnia jezyka JSON
        return "{" +
                "\"id\": " + id +
                ", \"name\": \"" + name + '\"' +
                ", \"lastName\": \"" + lastName + '\"' +
                ", \"email\": \"" + email + '\"' +
                ", \"password\": \"" + password + '\"'+
                ", \"category\": \"" + category + '\"' +
                ", \"subcategory\": \"" + subcategory + '\"' +
                ", \"phoneNumber\": \"" + phoneNumber + '\"' +
                ", \"birthDate\": \"" + birthDate + '\"' +
                "}";
}

}
