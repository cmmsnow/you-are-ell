package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.*;
import models.Id;
import models.Message;

import java.io.IOException;
import java.util.ArrayList;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;
    private TransactionController transactionController;

    public YouAreEll (MessageController m, IdController j, TransactionController t) {
        this.msgCtrl = m;
        this.idCtrl = j;
        this.transactionController = t;
    }

    public static void main(String[] args) throws IOException {
        YouAreEll urlhandler = new YouAreEll(new MessageController(), new IdController(), new TransactionController());
        System.out.println(urlhandler.methodSorter("/ids", "GET", ""));
        System.out.println(urlhandler.methodSorter("/messages", "GET", ""));
        //Add more of these ^^ for put and post
    }

    public boolean methodSorter(String mainurl, String method, String jpayload) throws IOException {
        if (method.equals("GET")) getURLCall(mainurl, jpayload);
        if (method.equals("POST")) postURLCall(mainurl, jpayload);
        if (method.equals("PUT")) putURLCall(mainurl, jpayload);
        return true;
    }

    public String getURLCall(String mainurl, String jpayload) throws IOException {
        if (mainurl.equals("/ids")){
            String response = transactionController.get(mainurl);
            ArrayList<Id> idsList = idCtrl.getIds(response);
            System.out.println(idsList);
        } else if (mainurl.equals("/messages")){
            String response = transactionController.get(mainurl);
            ArrayList<Message> messagesList = msgCtrl.getMessages(response);
            System.out.println(messagesList);
        }
        return "nada";
    }

    public String postURLCall(String mainurl, String jpayload) throws IOException {
        if (mainurl.equals("/ids")){
            String response = transactionController.post(mainurl);
            ArrayList<Id> idsList = idCtrl.getIds(response);
            System.out.println(idsList);
        } else if (mainurl.equals("/messages")){

        }
        return "nada";
    }

    public String putURLCall(String mainurl, String jpayload){
        if (mainurl.equals("/ids")){
            String response = transactionController.put(mainurl);
            //etc
        } else if (mainurl.equals("/messages")){

        }
        return "nada";
    }

}
