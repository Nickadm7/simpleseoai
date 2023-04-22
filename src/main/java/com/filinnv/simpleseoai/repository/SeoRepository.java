package com.filinnv.simpleseoai.repository;

import com.filinnv.simpleseoai.model.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeoRepository extends JpaRepository<RequestHistory, Long> {
}
