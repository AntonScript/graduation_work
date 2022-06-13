package com.example.graduation_work.api.dto.file;

import com.example.graduation_work.service.NormalizType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@Data
public class FileRequestDto {

    private List<NormalizType> normalizers;

    private boolean isRecording;

    private String nameFile;

    private String nameDocument;

    private String nameInspector;

    private  MultipartFile file;

    private UUID groupUid;

}
