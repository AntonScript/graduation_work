package com.example.graduation_work.mongo.repository;

import com.example.graduation_work.mongo.model.DocumentInfo;
import com.example.graduation_work.mongo.model.DocumentShort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentInfoMongoRepository extends MongoRepository<DocumentInfo, Long> {
}
