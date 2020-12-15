package controllers;

import java.util.ArrayList;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

public class MessageController {
    // part 3: can make the fromID intrinsic;
    // send messages by someone's name;
    // Create a means where the client watches the server
    // for any private messages to you and only prints them once;
    // Add another command that watches the global stream
    // and only prints messages once.

    private HashSet<Message> messagesSeen;

    public ArrayList<Message> getMessages(String response) throws JsonProcessingException {
        // URL: "/messages/" - Get last 20 msgs - returns an JSON array of message objects
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Message> messageList = objectMapper.readValue(response, new TypeReference<ArrayList<Message>>(){});
        return messageList; //returns a message object to MessageTextView
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }

    public Message getMessageForSequence(String seq) {
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        // URL: "/ids/:mygithubid/from/:friendgithubid"
        // Get last 20 msgs for myid from friendid
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}