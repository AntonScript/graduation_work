package com.example.graduation_work.service;

import com.example.graduation_work.domain.File;

/**
 * Сервис для нормализации файла.
 *
 * @author Anton Mamakin
 */
public interface NormalizHandler {

    public File fileNormalization(File file);

    public String getName();
}
