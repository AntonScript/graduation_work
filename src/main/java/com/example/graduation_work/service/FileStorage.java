package com.example.graduation_work.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileStorage {

    boolean saveFilt(MultipartFile file, String path);

    String getZip(List<String> paths);
}
