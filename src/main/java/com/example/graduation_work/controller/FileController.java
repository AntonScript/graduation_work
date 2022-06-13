package com.example.graduation_work.controller;


import com.example.graduation_work.api.FileApi;
import com.example.graduation_work.api.dto.file.FileRequestDto;
import com.example.graduation_work.api.dto.file.FileResponseDto;
import com.example.graduation_work.api.dto.file.ZipDto;
import com.example.graduation_work.jpa.entity.Document;
import com.example.graduation_work.jpa.repository.DocumentRepository;
import com.example.graduation_work.mongo.model.DocumentInfo;
import com.example.graduation_work.mongo.repository.DocumentInfoMongoRepository;
import com.example.graduation_work.mongo.repository.DocumentMongoRepository;
import com.example.graduation_work.service.FileService;
import com.example.graduation_work.service.FileStorage;
import com.example.graduation_work.service.impl.auth.UserDetailsImp;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FileController implements FileApi {

    private final FileService fileService;
    private final FileStorage fileStorage;

    private final DocumentRepository documentRepository;
    private final DocumentInfoMongoRepository documentInfoMongoRepository;
    private final DocumentMongoRepository documentMongoRepository;

    @Secured({"USER"})
    @Override
    public ResponseEntity<FileResponseDto> create(@ModelAttribute FileRequestDto fileRequestDto) {
        //documentMongoRepository.deleteAll();
        //documentInfoMongoRepository.deleteAll();
        return ResponseEntity.ok(fileService.verify(fileRequestDto));
    }

    @Secured({"TEACHER"})
    @Override
    public ResponseEntity<Void> save(@RequestParam Long id) {
        Boolean isSave = fileService.save(id);
        return isSave ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();

    }

    @Secured({"TEACHER"})
    @Override
    public ResponseEntity<ByteArrayResource> getFile(@RequestBody ZipDto dto) {
        String zip = fileStorage.getZip(dto.getPatch());
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(zip));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(new ByteArrayResource(bytes, zip));
    }

    @Secured({"USER", "TEACHER"})
    @Override
    public ResponseEntity<List<Document>> getFiles(@RequestParam(required = false) boolean isSave,
                                                   @RequestParam(required = false) Integer userId) {
        Optional<Integer> currentAuditor;
        if (userId == null) {
            currentAuditor = getCurrentAuditor();
        } else {
            currentAuditor = Optional.of(userId);
        }
        return currentAuditor.map(id -> ResponseEntity.ok(documentRepository.findByUserId(id)))
                             .orElse(ResponseEntity.badRequest().build());
    }

    @Override
    public ResponseEntity<DocumentInfo> getFilesInfo(@RequestParam Long id) {
        return ResponseEntity.ok(documentInfoMongoRepository.findById(id).get());
    }

    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        UserDetailsImp userDetailsImp = ((UserDetailsImp) authentication.getPrincipal());
        return Optional.ofNullable(userDetailsImp.getId());
    }
}
