package com.wrkr.coding.assessment.repository;

import com.wrkr.coding.assessment.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}