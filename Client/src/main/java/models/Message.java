package models;

/* 
 * POJO for an Message object
 */
public class Message {
    String message;
    String fromId;
    String toId;

    public Message (String message, String fromId, String toId) {
        this.message = message;
        this.fromId = fromId;
        this.toId = toId;
    }

}