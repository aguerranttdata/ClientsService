package com.group7.clientsservice.factories;

import com.group7.clientsservice.beans.BusinessClient;
import com.group7.clientsservice.beans.Client;
import com.group7.clientsservice.beans.PersonalClient;

public class ClientFactory {
    public static Client create(Client client){
        if (client instanceof PersonalClient){
            PersonalClient personalClient = (PersonalClient) client;
            personalClient.setType("Personal");
            return personalClient;
        }else if (client instanceof BusinessClient){
            BusinessClient businessClient = (BusinessClient) client;
            businessClient.setType("Business");
            return businessClient;
        }
        return null;
    }
}
