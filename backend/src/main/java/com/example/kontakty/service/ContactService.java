package com.example.kontakty.service;

import com.example.kontakty.models.Contact;
import com.example.kontakty.repo.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ContactService {
    @Autowired
    private ContactRepo contactRepo;

    public Iterable<Contact> getAllContacts(){ // Metoda do pobierania wszystkich kontaktów
        try {
                return contactRepo.findAll();

            }catch(Exception e){

                return null;
        }
    }

    public Contact getContact(long id){
        return contactRepo.findById(id).get();
    } // Metoda do pobierania kontaktu na podstawie ID
    public void createContact(Contact contact){
        contactRepo.save(contact);
    } // Metoda do tworzenia nowego kontaktu
    public void modifyContact(long id, Contact contact){ // Metoda do modyfikowania istniejącego kontaktu
        Contact modify = getContact(id);
        if(contact.getName() != null){ // Aktualizowanie poszczególnych pól kontaktu tylko wtedy, gdy są przekazane
            modify.setName(contact.getName());
        }
        if(contact.getLastName() != null){
            modify.setLastName(contact.getLastName());
        }
        if(contact.getEmail() != null){
            modify.setEmail(contact.getEmail());
        }
        if(contact.getPassword() != null){
            modify.setPassword(contact.getPassword());
        }
        if(contact.getCategory() != null){
            modify.setCategory(contact.getCategory());
        }
        if(contact.getSubcategory() != null){
            modify.setSubcategory(contact.getSubcategory());
        }
        if(contact.getPhoneNumber() != 0){
            modify.setPhoneNumber(contact.getPhoneNumber());
        }
        if(contact.getBirthDate() != null){
            modify.setBirthDate(contact.getBirthDate());
        }
        contactRepo.save(modify); // Zapisanie zmodyfikowanego kontaktu
    }
    public void deleteContact(long id){
        contactRepo.deleteById(id);
    } // Metoda do usuwania kontaktu na podstawie ID

}


