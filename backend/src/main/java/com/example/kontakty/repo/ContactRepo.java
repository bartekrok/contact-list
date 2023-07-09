package com.example.kontakty.repo;

import com.example.kontakty.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepo extends CrudRepository<Contact, Long> { // ContactRepo daje nam dostep do bazy danych. Daje nam dostep dzieki CrudRepository, ktory jest biblioteka dodana do frameworku Spring Boot

}
