package controllers;

import java.util.ArrayList;
import java.util.HashSet;

import models.Id;
import models.Message;

public class MessageController {
    // part 3: can make the fromID intrinsic;
    // send messages by someone's name;
    // Create a means where the client watches the server
    // for any private messages to you and only prints them once;
    // Add another command that watches the global stream
    // and only prints messages once.

    // CONVERT JSON STRING INTO JAVA CLASS example:
    // Player ronaldo = new ObjectMapper().readValue(jsonString, Player.class);

    private HashSet<Message> messagesSeen;
    // why a HashSet?? - idk to remove duplicates??

    public ArrayList<Message> getMessages() {
        // URL: "/messages/"
        // Get last 20 msgs - returns an JSON array of message objects
        return null;
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        // URL: "/ids/:mygithubid/messages/"
        // Get last 20 msgs for myid:
        // returns an JSON array of message objects
        return null;
    }

    public Message getMessageForSequence(String seq) {
        // URL: "/ids/:mygithubid/messages/:sequence"
        // returns a JSON message object for a sequence number
        return null;
    }

    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        // URL: "/ids/:mygithubid/from/:friendgithubid"
        // Get last 20 msgs for myid from friendid
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        // URL: "/ids/:mygithubid/messages/"
        // Create a new message in timeline:
        // Need to POST a new message object, and will get back one with a
        // message sequence number and timestamp of the server inserted
        // send as body of POST request to "http://zipcode.rocks:8085/ids/xt0fer/messages/"
        return null;
    }
 
}