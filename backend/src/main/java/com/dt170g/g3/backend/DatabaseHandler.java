package com.dt170g.g3.backend;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import com.dt170g.g3.backend.entities.Message;
import java.util.List;

@ApplicationScoped
@Named("dbHandler")
public class DatabaseHandler {
    @PersistenceContext
    EntityManager entityManager;

    public List<Message> getMessages(){
        TypedQuery<Message> messageQuery = entityManager.createNamedQuery("Message.getAll", Message.class);
        List<Message> resultList = messageQuery.getResultList();
        return resultList;
    }

    public String getMessageText(){
        List<Message> messageList = getMessages();
        if(messageList.isEmpty()){
            return "NO MESSAGES!";
        }
        return messageList.get(0).getText();
    }
}

