package com.lylastudio.catetduit.db.repository;

import com.lylastudio.catetduit.db.entity.MAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MAccountRespository extends JpaRepository<MAccount, Integer> {
    MAccount findByFromId(String chatId);
}
