package com.lylastudio.catetduit.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendMessage {

    @JsonProperty("chat_id")
    private long chatId;

    private String text;
}
