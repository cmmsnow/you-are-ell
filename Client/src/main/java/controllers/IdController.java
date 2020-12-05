package controllers;

import java.util.ArrayList;

import models.Id;

public class IdController {
    Id myId;
    //ids api url is: "/ids/"
    // GET, POST, PUT

    // CONVERT JSON STRING INTO JAVA CLASS example:
    // Player ronaldo = new ObjectMapper().readValue(jsonString, Player.class);

    public ArrayList<Id> getIds() {
        //get all githubIDs registered
        return null;
    }

    public Id postId(Id id) {
        //add your github id / name to be registered
        return null;
    }

    public Id putId(Id id) {
        //change the name linked to your githubID
        return null;
    }
 
}