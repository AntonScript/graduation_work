package com.example.graduation_work.service;

import com.example.graduation_work.domain.File;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


/**
 * Сервис для извлечения базой информации из файла на основание типа файла.
 *
 * @author Anton Mamakin
 */
public interface FileHandler {
    public List<String> getLinesFile(MultipartFile file) throws IOException;

    public String getName();
}
