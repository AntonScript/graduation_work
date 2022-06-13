package com.example.graduation_work.jpa.repository;

import com.example.graduation_work.jpa.entity.Document;
import com.example.graduation_work.jpa.entity.ServiceWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceWordsRepository extends JpaRepository<ServiceWords, Long> {

    boolean existsByValue(String value);
}
