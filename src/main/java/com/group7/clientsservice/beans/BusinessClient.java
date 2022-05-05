package com.group7.clientsservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessClient extends Client{
    private Long RUC;

    public BusinessClient(String id, String name, Long RUC){
        super(id,name,"Business");
        this.RUC = RUC;
    }
}
