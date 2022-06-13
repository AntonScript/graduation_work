package com.example.graduation_work.mongo.repository;

import com.example.graduation_work.mongo.model.DocumentShort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DocumentMongoRepository extends MongoRepository<DocumentShort, String> {

    Optional<DocumentShort> findByHash(String hash);

}
