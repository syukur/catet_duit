package com.lylastudio.catetduit.db.repository;

import com.lylastudio.catetduit.db.entity.TTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.math.BigInteger;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TTransaction, Integer> {
    public List<TTransaction> findByTelegramFromId(String telegramFromId);


    @Query(
            value = "SELECT SUM(t.amount) FROM t_transaction t WHERE t.telegram_from_id = :telegramFromId",
        nativeQuery = true
    )
    public BigInteger calculateAmountBySenderId(@Param("telegramFromId") String telegramFromId);

//    @Query(
//            value = "SELECT SUM(t.amount) FROM TTransaction t WHERE t.telegramFromId = :telegramFromId"
//    )
//    public BigInteger calculateAmountBySenderIdDua(@Param("telegramFromId") String telegramFromId);

}
