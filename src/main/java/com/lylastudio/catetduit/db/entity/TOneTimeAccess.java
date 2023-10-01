package com.lylastudio.catetduit.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

@Data
@Entity(name = "t_one_time_access")
public class TOneTimeAccess {

    @Id
    @SequenceGenerator(
            name = "m_one_time_access_sequence",
            sequenceName = "m_one_time_access_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "m_one_time_access_sequence"
    )
    private Integer Id;

    private String fromId;

    private String url;

    private String signature;

    private Date expired;

    private String parameter;

    private Date createdDate;

    private Date updatedDate;

    @PrePersist
    protected void onCreate(){
        createdDate = new Date();

    }


}
