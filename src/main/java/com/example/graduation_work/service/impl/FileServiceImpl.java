package com.example.graduation_work.service.impl;

import com.example.graduation_work.domain.File;
import com.example.graduation_work.service.FileHandler;
import com.example.graduation_work.service.FileService;
import com.example.graduation_work.service.NormalizHandler;
import com.example.graduation_work.service.NormalizType;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    private Map<String, FileHandler> fileHandlers;
    private Map<String, NormalizHandler> normalizersHandlers;

    @Autowired
    public FileServiceImpl(List<FileHandler> handlers, List<NormalizHandler> normalizerss) {
        normalizersHandlers =  new HashMap<>(normalizerss.size());
        fileHandlers =  new HashMap<>(handlers.size());
        handlers.stream().map(v -> fileHandlers.put(v.getName(), v));
        normalizerss.stream().map(v -> normalizersHandlers.put(v.getName(), v));
    }

    @Override
    public void verify(MultipartFile file, List<NormalizType> normalizers, boolean isRecording) {
        String typeFile = FilenameUtils.getExtension(file.getOriginalFilename());
        File baseFile = fileHandlers.get(typeFile).getLinesFile(file);
        normalizers.forEach(s -> normalizersHandlers.get(s).fileNormalization(baseFile));
    }

}
