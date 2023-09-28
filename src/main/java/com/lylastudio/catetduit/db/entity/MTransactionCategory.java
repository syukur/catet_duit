package com.lylastudio.catetduit.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "m_transaction_category")
public class MTransactionCategory {
    @Id
    @SequenceGenerator(
            name = "m_transaction_category_sequence",
            sequenceName = "m_transaction_category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "m_transaction_category_sequence"
    )
    private Integer id;

    private String name;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "m_account_id")
    private MAccount account;

    @OneToMany(
            mappedBy = "transactionCategory",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<TTransaction> transactions;

    @PrePersist
    protected void onCreate(){
        Date now = new Date();
        createdDate = now;
        updatedDate = now;
    }


}
