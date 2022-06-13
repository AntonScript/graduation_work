package com.example.graduation_work.jpa.repository;

import com.example.graduation_work.jpa.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findByHashFile(String hash);

    Optional<Document> findByNameFile(String fileName);

    List<Document> findByGroupUid(UUID uuid);

    List<Document> findByUserId(Integer id);
}
