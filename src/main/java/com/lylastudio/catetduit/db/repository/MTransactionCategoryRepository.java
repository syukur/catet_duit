package com.lylastudio.catetduit.db.repository;

import com.lylastudio.catetduit.db.entity.MTransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MTransactionCategoryRepository extends JpaRepository<MTransactionCategory, Integer> {
}
