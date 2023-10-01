package com.lylastudio.catetduit.db.repository;

import com.lylastudio.catetduit.db.entity.TOneTimeAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TOneTimeAccessRepository extends JpaRepository<TOneTimeAccess, Integer> {

    TOneTimeAccess findByFromIdAndUrl(String fromId, String url);

    TOneTimeAccess findBySignature(String signature);

}
