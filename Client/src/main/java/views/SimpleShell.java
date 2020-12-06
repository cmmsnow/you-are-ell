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
import youareell.YouAreEll;

// Simple Shell is a Console view for youareell.YouAreEll.
public class SimpleShell {


    public static void prettyPrint(String output) {
        String formattedString = "\n[%s]";
        String arg1 = output;
        String outputString = String.format(formattedString, arg1);
        System.out.println(outputString);
//        String body="{\n" +
//                "        \"userid\": \"-\",\n" +
//                "        \"name\": \"KillMoi\",\n" +
//                "        \"github\": \"ZZtop\"\n" +
//                "    }";
    }

    public static String commandMenu(){
        String get = "\nCOMMAND OPTIONS:\nTo get list of all users, type:  'ids'\nTo get list of all messages, type:  'messages'";
        String postId = "\nTo create a new user id, type:  'ids' 'your-name' 'your-github'";
        String putId ="\nTo change name for github, type:  'ids' 'new-name' 'same-github'";
        String postMessage = "\nTo post message, type:  'send' 'from-github' 'message' 'to' 'recipient-github'";
        String historyExit = "\nTo display shell history, type:  'history'\nTo display last command in history, type:  '!!'\nTo exit, type:  'exit'";
        System.out.println(get + postId + putId + postMessage + historyExit);
        return get + postId + putId + postMessage + historyExit;
    }


    public static void main(String[] args) throws java.io.IOException {
        String fromId = "";
        String toId = "";
        String payload = "";
        YouAreEll webber = new YouAreEll(new MessageController(), new IdController(), new TransactionController());
        
        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c> -- how to listen for this??
        while (true) {
            commandMenu();
            System.out.println("Command? ");
            commandLine = console.readLine();
            String[] commands = commandLine.split(" ");
            List<String> commandsList = new ArrayList<String>();
            //can I just: List<String> list = new ArrayList<String>(Arrays.asList(commands));

            //if the user entered a return, just loop again -- how??
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
            System.out.print(commandsList); //***check to see if list was added correctly*** <<how??
            history.addAll(commandsList);
            try {
                //display history of shell with index
                if (commandsList.get(commandsList.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                //get all ids
                if (commandsList.get(commandsList.size() - 1).equals("ids")) {
                    webber.getURLCall("/ids");
                    //SimpleShell.prettyPrint(results);
                    continue;
                }

                //get all messages
                //get all of 1 user's messages
                //get all messages between 2 people
                if (commandsList.get(commandsList.size() - 1).equals("messages")) {
                    webber.getURLCall("/messages");
                    //SimpleShell.prettyPrint(results);
                    continue;
                }

                //example: send xt0fer 'Hello old buddy!' to torvalds
                if (commandsList.contains("send")) {
                    fromId = commandsList.get(1);
                    toId = commandsList.get(4);
                    webber.postMessagesURLCall(fromId, toId, payload);
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