package com.group7.clientsservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="renato_clients3")
public class PersonalClient extends Client {
    private String firstName;
    private String lastName;

    public PersonalClient(String id, String documentType, Long documentNumber,String firstName, String lastName){
        super(id,"Personal",documentType,documentNumber);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}