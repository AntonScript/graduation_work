package com.example.graduation_work.controller;


import com.example.graduation_work.api.FileApi;
import com.example.graduation_work.api.dto.FileRequestDto;
import com.example.graduation_work.service.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController implements FileApi {

    private final FileService fileService;

    @Override
    public ResponseEntity<Long> create( @RequestPart("file") MultipartFile file,
                                        @RequestBody FileRequestDto dto) {
        fileService.verify(file, dto.getNormalizers(), dto.isRecording());
        return null;
    }
}
