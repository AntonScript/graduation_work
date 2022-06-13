package com.example.graduation_work.service.impl.file;

import com.example.graduation_work.jpa.entity.Document;
import com.example.graduation_work.jpa.repository.DocumentRepository;
import com.example.graduation_work.mongo.model.DocumentInfo;
import com.example.graduation_work.mongo.model.DocumentShort;
import com.example.graduation_work.mongo.repository.DocumentInfoMongoRepository;
import com.example.graduation_work.mongo.repository.DocumentMongoRepository;
import com.example.graduation_work.service.VerificationAlgorithm;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerificationAlgorithmImpl implements VerificationAlgorithm {

    private final DocumentMongoRepository documentMongoRepository;

    private final DocumentInfoMongoRepository documentInfoMongoRepository;

    //private final DocumentRepository documentRepository;

    private static final int SHIFT = 3;

    @Override
    public DocumentInfo searchForDuplicates(List<String> lines, Document document, Boolean isSave) {
        Map<Integer, List<DocumentShort>> result = new HashMap<>();
        List<DocumentShort> saveNew = new ArrayList<>();
        DocumentInfo documentInfo = new DocumentInfo();

        for (int i = 0; i < lines.size(); i++) {
            String[] s = lines.get(i).split(" ");
            if (s.length >= SHIFT) {
                for (int j = 0; j <= s.length - SHIFT; j++) {
                    String shingl = s[j] + s[j + 1] + s[j + 2];
                    String sha256hex = Hashing.sha256()
                                              .hashString(shingl, StandardCharsets.UTF_8)
                                              .toString();

                    Optional<DocumentShort> byHash = documentMongoRepository.findByHash(sha256hex);
                    if (byHash.isPresent()) {
                        if (result.containsKey(Integer.valueOf(i))) {
                            List<DocumentShort> documentShorts = result.get(Integer.valueOf(i));
                            documentShorts.add(byHash.get());
                            result.put(Integer.valueOf(i), documentShorts);
                        } else {
                            List<DocumentShort> documentShorts = new ArrayList<>();
                            documentShorts.add(byHash.get());
                            result.put(Integer.valueOf(i), documentShorts);

                        }
                    } else {
                        saveNew.add(new DocumentShort(sha256hex, document.getId(), i));
                    }
                }

            }
        }
        documentInfo.setPlagiarism(result);
        documentInfo.setDocumentId(document.getId());
        Double res = 100.0 - (result.keySet().size() * 100) / (double)lines.size();
        documentInfo.setPercentageOriginality(res);
        documentInfo.setListHashs(saveNew);
        documentInfoMongoRepository.save(documentInfo);
        if (isSave) {
            documentMongoRepository.saveAll(saveNew);
        }

        return documentInfo;
    }
}
