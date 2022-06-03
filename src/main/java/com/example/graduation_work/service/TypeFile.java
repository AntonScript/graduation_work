package com.example.graduation_work.service;

/**
 * Enum для описание возможных типов файлов достпуных для обработки.
 *
 * @author Anton Mamakin
 */
public enum TypeFile {
    TXT("txt"),
    WORD("word");

    private String typeFile;
    TypeFile(String typeFile) {
        this.typeFile = typeFile;
    }
    public String getTypeFile(){
        return typeFile;
    }
}
