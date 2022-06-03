package com.example.graduation_work.service;

import com.example.graduation_work.domain.File;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * Сервис для работы с файлами и их нормализацией.
 *
 * @author Anton Mamakin
 */
public interface FileService {

    public void verify(MultipartFile file, List<NormalizType> normalizers, boolean isRecording);

}
