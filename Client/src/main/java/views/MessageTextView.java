package views;

import models.Message;

public class MessageTextView {
    Message message;

    public MessageTextView(Message msgToDisplay) {
        this.message = msgToDisplay;
    }

    @Override public String toString() {
        String toId = message.getToid();
        if (toId.equals("")){
            toId = "(everybody)";
        }
        return String.format("\nSequence: %s,  Timestamp: %s,  From: %s,  To: %s,  Message: %s", message.getSequence(), message.getTimestamp(), message.getFromid(), toId, message.getMessage());
    } 
}