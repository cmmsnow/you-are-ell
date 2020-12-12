package youareell;

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
    }

    public ArrayList<Id> getIdURLCall(String mainurl) throws IOException {
        String response = transactionController.get(mainurl);
        ArrayList<Id> idsList = idCtrl.getIds(response);
        System.out.println(idsList);
        return idsList;
    }

    public ArrayList<Message> getMessagesURLCall(String mainurl) throws IOException {
        String response = transactionController.get(mainurl);
        ArrayList<Message> messagesList = msgCtrl.getMessages(response);
        System.out.println(messagesList);
        return messagesList;
    }

    public ArrayList<Id> postIdsURLCall(String name, String github) throws IOException {
        Id idObject = new Id(name, github, "-");
        String response = transactionController.postIds(idObject);
        ArrayList<Id> idsList = idCtrl.getIds(response);
        System.out.println(idsList);
        return idsList;
    }

    public ArrayList<Id> putIdsURLCall(String userid, String name, String github) throws IOException {
        Id idObject = new Id(name, github, userid);
        String response = transactionController.putIds(idObject);
        ArrayList<Id> idsList = idCtrl.getIds(response);
        System.out.println(idsList);
        return idsList;
    }

    public ArrayList<Message> postMessagesURLCall(String fromId, String toId, String jpayload) throws IOException {
        Message message = new Message(jpayload, fromId, toId);
        String response = transactionController.postMessages(toId, message);
        ArrayList<Message> messagesList = msgCtrl.getMessages(response);
        System.out.println(messagesList);
        return messagesList;
    }

}
