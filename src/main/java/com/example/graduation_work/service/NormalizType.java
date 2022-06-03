package com.example.graduation_work.service;

/**
 * Enum для описание возможных типов нормализации файла.
 *
 * @author Anton Mamakin
 */
public enum NormalizType {
    TXT("default");

    private String normalizFile; // Could be other data type besides int
    NormalizType(String typeFile) {
        this.normalizFile = normalizFile;
    }
    public String getTypeFile(){
        return normalizFile;
    }
}
