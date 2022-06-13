package com.example.graduation_work.service;

import java.util.List;

/**
 * Enum для описание возможных типов нормализации файла.
 *
 * @author Anton Mamakin
 */
public enum NormalizType {
    DEFAULT("default"),
    SERVICE_WORDS("serviceWords");

    private String normalizFile;
    NormalizType(String typeFile) {
        this.normalizFile = normalizFile;
    }
    public String getTypeFile(){
        return normalizFile;
    }

    public static List<String> getBase() {
        return List.of(DEFAULT.name(), SERVICE_WORDS.name());
    }
}
