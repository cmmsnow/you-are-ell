package models;
import views.IdTextView;
import views.MessageTextView;


public class Message {
    private String message;
    private String fromid;
    private String toid;
    private String sequence;
    private String timestamp;

    public Message (String message, String fromid, String toid, String sequence, String timestamp) {
        this.message = message;
        this.fromid = fromid;
        this.toid = toid;
        this.sequence = sequence;
        this.timestamp = timestamp;
    }

    public Message (String message, String fromid, String toid) {
        this.message = message;
        this.fromid = fromid;
        this.toid = toid;
        this.sequence = "";
        this.timestamp = "";
    }

    public Message(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        MessageTextView messageTextView = new MessageTextView(this);
        return messageTextView.toString();
    }
}