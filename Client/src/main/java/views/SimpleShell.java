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

// STILL BROKEN: post message & put ID
public class SimpleShell {


    public static void prettyPrint(String output) {
        String formattedString = "\n[%s]";
        String arg1 = output;
        String outputString = String.format(formattedString, arg1);
        System.out.println(outputString);
    }

    public static String commandMenu(){
        String get = "\nCOMMAND OPTIONS:\nTo get list of all users, type:  'ids'\nTo get list of all messages, type:  'messages'";
        String postId = "\nTo create a new user id, type:  ids your-name your-github";
        String putId ="\nTo change name for github, type:  ids new-name same-github";
        String postMessage = "\nTo post message, type:  send your-github 'your message' to recipient-github";
        String historyExit = "\nTo display shell history, type:  history\nTo exit, type:  exit";
        //need to add one for get messages spf to UserID
        //and one for getting messages between 2 users
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
        //we break out with <ctrl c> -- how to listen for this??
        while (true) {
            commandMenu();
            Boolean written = false;
            System.out.println("Command? ");
            //if the user entered a return, just loop again
            while (!written){
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

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                System.out.println(commands[i]); //***check to see if parsing/split worked***
                commandsList.add(commands[i]);
            }
            System.out.print(commandsList); //***check to see if list was added correctly***
            history.addAll(commandsList);
            try {
                //display history of shell with index
                if (commandsList.get(commandsList.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                //get/post/put ids
                if (commandsList.contains("ids")) {
                    if (commandsList.get(0).equals("ids") && commandsList.size() == 1) {
                        webber.getIdURLCall("/ids");
                        continue;
                    } else if (commandsList.get(0).equals("ids") && commandsList.size() == 3) {
                        boolean found=false;
                        ArrayList<Id> idsList = webber.getIdURLCall("/ids");
                        for (int i = 0; i < idsList.size(); i++) {
                            if (idsList.get(i).getGithub().equals(commandsList.get(2))) {
                                idsList.get(i).setName(commandsList.get(1));
                                //what is broken here?!
                                webber.putIdsURLCall(idsList.get(i).getUserid(), idsList.get(i).getName(), idsList.get(i).getGithub());
                                found = true;
                                continue;
                            }
                        }
                        if (!found) {
                            webber.postIdsURLCall(commandsList.get(1), commandsList.get(2));
                            continue;
                        }
                    }
                }

                //get all messages
                if (commandsList.get(commandsList.size() - 1).equals("messages")) {
                    webber.getMessagesURLCall("/messages");
                    continue;
                }

                //example: send xt0fer 'Hello old buddy!' to torvalds
                //something about this is still broken, idk what
                if (commandsList.contains("send")) {
                    String fromId = commandsList.get(1);
                    String message = commandsList.get(2);
                    String toId = commandsList.get(commandsList.size()-1);
                    int messageEnd = 2;
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

                // wait, wait, what curiousness is this? < no idea
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
            // So what, do you suppose, is the meaning of this comment?
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