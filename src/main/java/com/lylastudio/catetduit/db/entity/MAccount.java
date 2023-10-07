package com.lylastudio.catetduit.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "m_account")
public class MAccount {

    @Id
    @SequenceGenerator(
            name = "m_account_sequence",
            sequenceName = "m_account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "m_account_sequence"
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
