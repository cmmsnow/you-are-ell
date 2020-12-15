package views;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import youareell.YouAreEll;

public class SimpleShell {


    public static void prettyPrint(String output) {
        String formattedString = "\n[%s]";
        String arg1 = output;
        String outputString = String.format(formattedString, arg1);
        System.out.println(outputString);
    }

    public static String commandMenu(){
        String get = "\nCOMMAND OPTIONS:\nTo get list of all users, type:      | ids |\nTo get list of all messages, type:   | messages |";
        String postId = "\nTo create a new user id, type:       | ids | your-name | your-github |";
        String putId ="\nTo change name for github, type:     | ids | user-id | new-name | same-github |";
        String postMessage = "\nTo post message, type:               | send | your-github | 'your message' | to | recipient-github |";
        String historyExit = "\nTo display shell history, type:      | history |\nTo exit, type:                       | exit |";
        System.out.println(get + postId + putId + postMessage + historyExit);
        return get + postId + putId + postMessage + historyExit;
    }

    public static void main(String[] args) throws java.io.IOException {
        YouAreEll webber = new YouAreEll(new MessageController(), new IdController(), new TransactionController());
        
        String commandLine = "";
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        while (true) {
            commandMenu();
            Boolean written = false;
            System.out.println("Command? ");
            while (!written){ // if the user entered a return, this will just loop again
                commandLine = console.readLine();
                if (!commandLine.equals("")) written = true;
            }
            String[] commands = commandLine.split(" ");
            List<String> commandsList = new ArrayList<String>();

            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            for (int i = 0; i < commands.length; i++) {
                System.out.println(commands[i]); // this checks to see if parsing/split worked
                commandsList.add(commands[i]);
            }
            System.out.print(commandsList); //this checks to see if list was added correctly
            history.addAll(commandsList);
            try {
                //display history of shell with index
                if (commandsList.get(commandsList.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                //get ids
                if (commandsList.get(0).equals("ids") && commandsList.size() == 1) {
                    webber.getIdURLCall("/ids");
                    continue;
                }

                //post ids
                if (commandsList.get(0).equals("ids") && commandsList.size() == 3){
                    webber.postIdsURLCall(commandsList.get(1), commandsList.get(2));
                    continue;
                }

                //put ids
                if (commandsList.get(0).equals("ids") && commandsList.size() == 4){
                    webber.putIdsURLCall(commandsList.get(1), commandsList.get(2), commandsList.get(3));
                    continue;
                }

                //get/post/put ids -- a different way to write them.
//                if (commandsList.contains("ids")) {
//                    if (commandsList.get(0).equals("ids") && commandsList.size() == 1) {
//                        webber.getIdURLCall("/ids");
//                        continue;
//                    } else if (commandsList.get(0).equals("ids") && commandsList.size() == 3) {
//                        boolean found=false;
//                        ArrayList<Id> idsList = webber.getIdURLCall("/ids");
//                        for (int i = 0; i < idsList.size(); i++) {
//                            if (idsList.get(i).getGithub().equals(commandsList.get(2))) {
//                                idsList.get(i).setName(commandsList.get(1));
//                                //what is broken here?!
//                                webber.putIdsURLCall(idsList.get(i).getUserid(), idsList.get(i).getName(), idsList.get(i).getGithub());
//                                found = true;
//                                continue;
//                            }
//                        }
//                        if (!found) {
//                            webber.postIdsURLCall(commandsList.get(1), commandsList.get(2));
//                            continue;
//                        }
//                    }
//                }

                //get all messages
                if (commandsList.get(commandsList.size() - 1).equals("messages")) {
                    webber.getMessagesURLCall("/messages");
                    continue;
                }

                //send message to user
                //example: send xt0fer 'Hello old buddy!' to torvalds
                if (commandsList.contains("send")) {
                    String fromId = commandsList.get(1);
                    String message = commandsList.get(2);
                    String toId = commandsList.get(commandsList.size()-1);
                    int messageEnd = 2;
                    //need to look for quotes instead of 'to'
                    for (int i=0; i<commandsList.size(); i++){
                        if (commandsList.get(i).equals("to")) messageEnd = i-1;
                    }
                    if (messageEnd > 2){
                        for (int i=3; i<=messageEnd; i++){
                            message += commandsList.get(i);
                        }
                    }
                    webber.postMessagesURLCall(fromId, toId, message);
                    continue;
                }

                //!! command returns the last command in history
                if (commandsList.get(commandsList.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (commandsList.get(commandsList.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(commandsList.get(commandsList.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(commandsList);
                }

                Process process = pb.start();

                //obtain the input stream
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //read output of the process
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
                br.close();
            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                System.out.println("Input Error, Please try again!");
            }
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */
        }
    }
}