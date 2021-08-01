package com.example.atry;

public class MessageModel {

    String Uid,message,messageId;
    Long timestamp;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MessageModel(String uid, String message) {
        Uid = uid;
        this.message = message;
    }

    public MessageModel() {}

    public MessageModel(String uid, String message, Long timestamp) {
        Uid = uid;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
