package com.example.graduation_work.service;

import com.example.graduation_work.domain.File;
import java.util.List;

/**
 * Сервис для нормализации файла.
 *
 * @author Anton Mamakin
 */
public interface NormalizHandler {

    List<String> fileNormalization(List<String> file);

    public String getName();
}
