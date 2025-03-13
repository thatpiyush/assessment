package com.wrkr.coding.assessment.dto;

import com.wrkr.coding.assessment.model.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookRequest {

    @NotEmpty(message = "Address book name cannot be empty")
    @Schema(description = "Name of Address Book")
    private String name;

    @NotEmpty(message = "Contact list cannot be empty")
    @Schema(description = "List of Contacts")
    private List<Contact> contacts;
}
