package com.lylastudio.catetduit.db.repository;

import com.lylastudio.catetduit.db.entity.MHandler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MHandlerRepository extends JpaRepository<MHandler, Integer> {
    List<MHandler> findByDescriptionNot(String description);
}
