package com.lylastudio.catetduit.model.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class From {
    private long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("username")
    private String userName;
}
