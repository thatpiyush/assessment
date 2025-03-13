package com.wrkr.coding.assessment.repository;

import com.wrkr.coding.assessment.model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {
    Optional<AddressBook> findByName(String name);
}