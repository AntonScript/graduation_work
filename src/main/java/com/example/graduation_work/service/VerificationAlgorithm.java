package com.example.graduation_work.service;

import com.example.graduation_work.jpa.entity.Document;
import com.example.graduation_work.mongo.model.DocumentInfo;
import com.example.graduation_work.mongo.model.DocumentShort;
import java.util.List;
import java.util.Map;

public interface VerificationAlgorithm {

    public DocumentInfo searchForDuplicates(List<String> lines, Document document, Boolean isSave);
}
