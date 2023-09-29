package com.lylastudio.catetduit.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@Entity(name = "t_transaction")
public class TTransaction {

    @Id
    @SequenceGenerator(
            name = "t_transaction_sequence",
            sequenceName = "t_transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "t_transaction_sequence"
    )
    private Integer id;

    private BigInteger amount;

    private String note;

    private String name;

    @Column(name = "telegram_from_id")
    private String telegramFromId;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(
            name = "m_category_id"
    )
    private MTransactionCategory transactionCategory;

    @PrePersist
    protected void onCreate(){
        createdDate = new Date();
    }

}
