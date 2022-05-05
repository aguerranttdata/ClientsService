package com.group7.clientsservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="renato_clients3")
public class BusinessClient extends Client{
    private String socialName;

    public BusinessClient(String id, String documentType, Long documentNumber,String socialName){
        super(id,"Business",documentType,documentNumber);
        this.socialName = socialName;
    }
}