package youareell;

import controllers.*;
import models.Id;
import okhttp3.Response;

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
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(new MessageController(), new IdController(), new TransactionController());
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

//    public String get_ids() {
//        return MakeURLCall("/ids", "GET", "");
//    }
//
//    public String get_messages() {
//        return MakeURLCall("/messages", "GET", "");
//    }

    public String MakeURLCall(String mainurl, String method, String jpayload) throws IOException {
        if (mainurl.equals("/ids")){
            String response = transactionController.get(mainurl);
            ArrayList<Id> idsList = idCtrl.getIds(response);
            System.out.println(idsList);

        } else if (mainurl.equals("/messages")){
            // call transaction controller
            // transaction controller returns response
            // send response to MessageController
        }
        return "nada";
    }
}
