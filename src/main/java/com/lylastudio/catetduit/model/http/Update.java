package com.lylastudio.catetduit.model.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Update {
    @JsonProperty("update_id")
    private long updateId;

    private Message message;
}
