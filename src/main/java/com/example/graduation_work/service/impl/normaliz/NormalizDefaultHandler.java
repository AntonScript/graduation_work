package com.example.graduation_work.service.impl.normaliz;

import com.example.graduation_work.jpa.repository.ServiceWordsRepository;
import com.example.graduation_work.service.NormalizHandler;
import com.example.graduation_work.service.NormalizType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NormalizDefaultHandler implements NormalizHandler {

    @Override
    public List<String> fileNormalization(List<String> linesWord) {
        linesWord = toLowerCase(linesWord);
        linesWord = clearingCharacters(linesWord);
        linesWord = clearingSpace(linesWord);

        return linesWord;
    }

    private List<String> toLowerCase(List<String> linesWord) {
        return linesWord.stream().map(s -> s.toLowerCase(Locale.ROOT)).collect(Collectors.toList());
    }

    private List<String> clearingCharacters(List<String> linesWord) {
        return linesWord.stream().map(s -> s.replaceAll("\\p{P}", "")).collect(Collectors.toList());
    }

    private List<String> clearingSpace(List<String> linesWord) {
        return linesWord.stream().map(s -> s.replaceAll("\\s+"," ").trim()).collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return NormalizType.DEFAULT.name();
    }
}
