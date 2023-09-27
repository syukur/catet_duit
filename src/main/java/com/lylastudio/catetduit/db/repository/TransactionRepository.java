package com.lylastudio.catetduit.db.repository;

import com.lylastudio.catetduit.db.entity.TTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TTransaction, Integer> {
}
