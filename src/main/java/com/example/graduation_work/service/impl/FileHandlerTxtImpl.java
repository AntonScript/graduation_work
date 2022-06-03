package com.example.graduation_work.service.impl;

import com.example.graduation_work.domain.File;
import com.example.graduation_work.service.FileHandler;
import com.example.graduation_work.service.TypeFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileHandlerTxtImpl implements FileHandler {

    @Override
    public File getLinesFile(MultipartFile file) {
        return null;
    }

    @Override
    public String getName() {
        return TypeFile.TXT.getTypeFile();
    }
}
