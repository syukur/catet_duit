package com.lylastudio.catetduit.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Data
@Entity(name = "m_handler")
public class MHandler {

    @Id
    @SequenceGenerator(
            name = "m_handler_sequence",
            sequenceName = "m_handler_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;

    private String keyword;

    private String handler;

    private String responseFormat;



}
