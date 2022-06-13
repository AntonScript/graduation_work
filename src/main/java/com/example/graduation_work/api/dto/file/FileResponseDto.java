package com.example.graduation_work.api.dto.file;

import com.example.graduation_work.mongo.model.DocumentInfo;
import com.example.graduation_work.mongo.model.DocumentShort;
import lombok.Data;
import java.util.Map;

@Data
public class FileResponseDto {

    private Long id;

    private DocumentInfo duplicates;

    private Long count;
}
