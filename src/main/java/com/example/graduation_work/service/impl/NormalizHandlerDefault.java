package com.example.graduation_work.service.impl;

import com.example.graduation_work.domain.File;
import com.example.graduation_work.service.NormalizHandler;
import org.springframework.web.multipart.MultipartFile;

public class NormalizHandlerDefault implements NormalizHandler {
    @Override
    public File fileNormalization(File file) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
