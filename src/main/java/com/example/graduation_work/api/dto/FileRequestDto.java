package com.example.graduation_work.api.dto;

import com.example.graduation_work.service.NormalizType;
import lombok.Data;
import java.util.List;

@Data
public class FileRequestDto {

    private List<NormalizType> normalizers;

    private boolean isRecording;
}
