package com.example.graduation_work.service.impl.normaliz;

import com.example.graduation_work.jpa.repository.ServiceWordsRepository;
import com.example.graduation_work.service.NormalizHandler;
import com.example.graduation_work.service.NormalizType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NormalizClearingServiceWordsHandler implements NormalizHandler {

    private final ServiceWordsRepository serviceWordsRepository;

    @Override
    public List<String> fileNormalization(List<String> strings) {
        return strings.stream().map(s -> Arrays.stream(s.split(" "))
                                               .filter(str -> !serviceWordsRepository.existsByValue(str))
                                               .collect(Collectors.joining(" ")))
                      .collect(Collectors.toList());
    }


    @Override
    public String getName() {
        return NormalizType.SERVICE_WORDS.name();
    }
}
