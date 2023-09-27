package com.lylastudio.catetduit.model.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {
    private long date;

    private Chat chat;

    @JsonProperty("message_id")
    private int messageId;

    private From from;

    private String text;
}
