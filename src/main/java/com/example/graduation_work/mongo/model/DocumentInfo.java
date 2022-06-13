package com.example.graduation_work.mongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class  DocumentInfo {

    @Id
    private Long documentId;

    private Double percentageOriginality;

    private Map<Integer, List<DocumentShort>> plagiarism;

    @JsonIgnore
    private List<DocumentShort> listHashs;
}
