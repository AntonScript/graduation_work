package com.example.graduation_work.service.impl.file;

import com.example.graduation_work.domain.File;
import com.example.graduation_work.service.FileHandler;
import com.example.graduation_work.service.TypeFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileHandlerTxtImpl implements FileHandler {

    @Override
    public List<String> getLinesFile(MultipartFile file) throws IOException {
        String result;
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(file.getInputStream())).lines()) {
            result = lines.collect(Collectors.joining(" "));
        }

        return separator(result);
    }

    private List<String> separator(String s) {
        s = s.replace("!", ".").replace("?", ".").replace("\n","").replace("\t","").replace("\\s+"," ");

        String[] split = s.split("\\.");
        List<String> collect = Arrays.stream(split).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String getName() {
        return TypeFile.TXT.getTypeFile();
    }
}
