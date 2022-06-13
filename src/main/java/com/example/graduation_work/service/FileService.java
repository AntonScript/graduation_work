package com.example.graduation_work.service;

import com.example.graduation_work.api.dto.file.FileRequestDto;
import com.example.graduation_work.api.dto.file.FileResponseDto;

/**
 * Сервис для работы с файлами и их нормализацией.
 *
 * @author Anton Mamakin
 */
public interface FileService {

    FileResponseDto verify(FileRequestDto dto);

    Boolean save(Long id);

}
