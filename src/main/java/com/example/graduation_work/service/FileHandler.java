package com.example.graduation_work.service;

import com.example.graduation_work.domain.File;
import org.springframework.web.multipart.MultipartFile;


/**
 * Сервис для извлечения базой информации из файла на основание типа файла.
 *
 * @author Anton Mamakin
 */
public interface FileHandler {
    public File getLinesFile(MultipartFile file);

    public String getName();
}
