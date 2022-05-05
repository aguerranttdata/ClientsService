package com.group7.clientsservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalClient extends Client {
    private Long DNI;
    private String email;

    public PersonalClient(String id, String name, Long DNI, String email){
        super(id,name,"Personal");
        this.DNI = DNI;
        this.email = email;
    }
}
