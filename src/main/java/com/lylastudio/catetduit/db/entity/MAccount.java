package com.lylastudio.catetduit.db.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "m_account")
public class MAccount {

    @Id
    @SequenceGenerator(
            name = "m_handler_sequence",
            sequenceName = "m_handler_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "m_handler_sequence"
    )
    private Integer id;

    private String firstName;

    private String lastName;

    private String chatId;

    private String fromId;

    private String userName;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<MTransactionCategory> transactionCategories;

}
