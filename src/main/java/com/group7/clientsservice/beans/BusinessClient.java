package com.group7.clientsservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="renato_business_clients2")
public class BusinessClient extends Client{
    private Long RUC;

    public BusinessClient(String id, String name, Long RUC){
        super(id,name,"Business");
        this.RUC = RUC;
    }
}