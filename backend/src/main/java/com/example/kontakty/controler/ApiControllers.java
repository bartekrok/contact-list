package com.example.kontakty.controler;

import com.example.kontakty.models.Contact;
import com.example.kontakty.service.ContactService;
//import com.example.kontakty.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class ApiControllers {

    private ContactService contactService;

    @Autowired
    public ApiControllers(ContactService contactService){
        this.contactService = contactService;
    }
    // Endpoint do pobierania kontaktu na podstawie ID
    @GetMapping("/contacts/{id}")
    public String getContact(@PathVariable long id){
        return contactService.getContact(id).toString();
    } // po stronie uzytkownika
    // Endpoint do pobierania wszystkich kontaktów
    @GetMapping("/contacts")
    public String getAllContacts(){
        return contactService.getAllContacts().toString();
    } // po stronie uzytkownika
    // Endpoint do pobierania wszystkich kontaktów (tylko dla administratora)
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @GetMapping("/contactsAdmin")
    public String getAllContactsA(){
        return contactService.getAllContacts().toString();
    } // po stronie admina
    // Endpoint do tworzenia kontaktu (tylko dla administratora)
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @PostMapping("/contactsAdmin")
    public void createContact(@RequestBody Contact contact){contactService.createContact(contact);}// po stronie admina
    // Endpoint do modyfikowania kontaktu (tylko dla administratora)
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @PutMapping("/contactsAdmin/{id}")
    public void modifyContact(@PathVariable long id, @RequestBody Contact newContact){contactService.modifyContact(id,newContact);}// po stronie admina
    // Endpoint do usuwania kontaktu (tylko dla administratora)
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @DeleteMapping("/contactsAdmin/{id}")
    public void deleteContact(@PathVariable long id){contactService.deleteContact(id);}// po stronie admina






}




