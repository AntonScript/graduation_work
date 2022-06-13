package com.example.graduation_work.mongo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
public class DocumentShort {

    public DocumentShort(String hash) {
        this.hash = hash;
    }

    public DocumentShort(String hash, Long documentId, Integer numberLine) {
        this.hash = hash;
        this.documentId = documentId;
        this.numberLine = numberLine;
    }

    @Id
    private String hash;

    private Long documentId;

    private Integer numberLine;

    private UUID groupUid;

}
