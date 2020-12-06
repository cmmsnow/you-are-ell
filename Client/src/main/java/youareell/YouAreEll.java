package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import controllers.*;
import models.Id;
import models.Message;

import java.io.IOException;
import java.util.ArrayList;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;
    private TransactionController transactionController;

    public YouAreEll(MessageController m, IdController j, TransactionController t) {
        this.msgCtrl = m;
        this.idCtrl = j;
        this.transactionController = t;
    }

    public static void main(String[] args) throws IOException {
        YouAreEll urlhandler = new YouAreEll(new MessageController(), new IdController(), new TransactionController());
        //System.out.println(urlhandler.methodSorter("/ids", "GET", ""));
        System.out.println(urlhandler.methodSorter("/messages", "GET", ""));
        //System.out.println(urlhandler.methodSorter("/ids", "POST", ""));
        //System.out.println(urlhandler.methodSorter("/ids", "PUT", ""));
        //System.out.println(urlhandler.methodSorter("/messages", "GET", ""));
        //System.out.println(urlhandler.methodSorter("/messages", "POST", ""));
        //Change ids put & post when i get user input from SimpleShell?
    }

    public boolean methodSorter(String mainurl, String method, String jpayload) throws IOException {
        if (method.equals("GET")) getURLCall(mainurl, jpayload);
        if (method.equals("POST") && mainurl.equals("/ids")) postIdsURLCall(mainurl, jpayload);
        if (method.equals("PUT") && mainurl.equals("/ids")) putIdsURLCall(mainurl, jpayload);
        if (method.equals("POST") && mainurl.contains("/messages")) postMessagesURLCall(mainurl, jpayload);
        //need to add one for get messages spf to UserID
        //and one for getting messages between 2 users
        return true;
    }

    public String getURLCall(String mainurl, String jpayload) throws IOException {
        if (mainurl.equals("/ids")) {
            String response = transactionController.get(mainurl);
            ArrayList<Id> idsList = idCtrl.getIds(response);
            System.out.println(idsList);
        } else if (mainurl.equals("/messages")) {
            String response = transactionController.get(mainurl);
            ArrayList<Message> messagesList = msgCtrl.getMessages(response);
            System.out.println(messagesList);
        }
        return "nada";
    }

    public String postIdsURLCall(String mainurl, String jpayload) throws IOException {
            String response = transactionController.postIds(mainurl);
            //ArrayList<Id> idsList = idCtrl.getIds(response);
            //System.out.println(idsList);
            return "nada";
    }

    public String putIdsURLCall(String mainurl, String jpayload) throws IOException {
            String response = transactionController.putIds(mainurl);
            //ArrayList<Id> idsList = idCtrl.getIds(response);
            //System.out.println(idsList);
            return "nada";
    }

    public String postMessagesURLCall(String mainurl, String jpayload) throws IOException {
        String response = transactionController.postMessages("/ids" + "/cmmsnow" + "/messages");
        //ArrayList<Id> idsList = idCtrl.getIds(response);
        //System.out.println(idsList);
        return "nada";
    }

}
