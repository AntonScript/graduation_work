package com.example.graduation_work.domain;

import lombok.Data;
import java.util.List;

@Data
public class File {

    private String nameFile;

    private String nameAuthor;

    private String nameInspector;

    private List<String> lines;
}
