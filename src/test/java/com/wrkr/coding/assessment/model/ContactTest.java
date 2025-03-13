package com.wrkr.coding.assessment.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidContact() {
        Contact contact = new Contact();
        contact.setName("Sample Test");
        contact.setContactNumber("+1 123-456-7890");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        assertEquals(0, violations.size());
    }

    @Test
    public void testInvalidName() {
        Contact contact = new Contact();
        contact.setName("Test123");
        contact.setContactNumber("+1 123-456-7890");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        assertEquals(1, violations.size());
    }

    @Test
    public void testInvalidContactNumber() {
        Contact contact = new Contact();
        contact.setName("Sample Test");
        contact.setContactNumber("1234");

        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        assertEquals(1, violations.size());
    }
}

