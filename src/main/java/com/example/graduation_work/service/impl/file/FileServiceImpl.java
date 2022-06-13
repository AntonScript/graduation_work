package com.example.graduation_work.service.impl.file;

import com.example.graduation_work.api.dto.file.FileRequestDto;
import com.example.graduation_work.api.dto.file.FileResponseDto;
import com.example.graduation_work.config.exception.ApplicationException;
import com.example.graduation_work.jpa.entity.Document;
import com.example.graduation_work.jpa.entity.User;
import com.example.graduation_work.jpa.repository.DocumentRepository;
import com.example.graduation_work.jpa.repository.UserRepo;
import com.example.graduation_work.mongo.model.DocumentInfo;
import com.example.graduation_work.mongo.model.DocumentShort;
import com.example.graduation_work.mongo.repository.DocumentInfoMongoRepository;
import com.example.graduation_work.mongo.repository.DocumentMongoRepository;
import com.example.graduation_work.service.FileHandler;
import com.example.graduation_work.service.FileService;
import com.example.graduation_work.service.FileStorage;
import com.example.graduation_work.service.NormalizHandler;
import com.example.graduation_work.service.NormalizType;
import com.example.graduation_work.service.VerificationAlgorithm;
import com.example.graduation_work.service.impl.auth.UserDetailsImp;
import com.google.common.hash.Hashing;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class FileServiceImpl implements FileService {

    private final DocumentRepository documentRepository;

    private final VerificationAlgorithm verificationAlgorithm;

    private final FileStorage fileStorage;

    private final DocumentMongoRepository documentMongoRepository;

    private final DocumentInfoMongoRepository documentInfoMongoRepository;

    private final UserRepo userRepo;

    private Map<String, FileHandler> fileHandlers;
    private Map<String, NormalizHandler> normalizersHandlers;

    private static final String DUPLICATE_FILE_ERROR = "Файл с такой хеш-суммой уже существует, id= {1}";
    private static final String DUPLICATE_FILE_NAME_ERROR = "Файл с таким именем уже существует, id= {1}";

    @Autowired
    public FileServiceImpl(
        DocumentRepository documentRepository,
        VerificationAlgorithm verificationAlgorithm, FileStorage fileStorage,
        DocumentMongoRepository documentMongoRepository,
        DocumentInfoMongoRepository documentInfoMongoRepository,
        UserRepo userRepo, List<FileHandler> handlers,
        List<NormalizHandler> normalizerss) {
        this.documentRepository = documentRepository;
        this.verificationAlgorithm = verificationAlgorithm;
        this.fileStorage = fileStorage;
        this.documentMongoRepository = documentMongoRepository;
        this.documentInfoMongoRepository = documentInfoMongoRepository;
        this.userRepo = userRepo;
        normalizersHandlers = new HashMap<>(normalizerss.size());
        fileHandlers = new HashMap<>(handlers.size());
        handlers.forEach(v -> fileHandlers.put(v.getName(), v));
        normalizerss.forEach(v -> normalizersHandlers.put(v.getName(), v));
    }

    @Transactional
    @Override
    public FileResponseDto verify(FileRequestDto dto) {

        FileResponseDto result = new FileResponseDto();
        String hashCode;
        try {
            hashCode = Hashing.sha256().hashBytes(dto.getFile().getBytes()).toString();

        } catch (IOException e) {
            throw new ApplicationException("Ошибка при работе с файловой системой, попробуйте попытку позже");
        }

        Optional<Document> byHashFile = documentRepository.findByHashFile(hashCode);
        if (byHashFile.isPresent()) {
            throw new ApplicationException(String.format(DUPLICATE_FILE_ERROR, byHashFile.get().getId()));
        }

        Optional<Document> byNameFile = documentRepository.findByNameFile(dto.getNameFile());
        if (byNameFile.isPresent()) {
            throw new ApplicationException(String.format(DUPLICATE_FILE_NAME_ERROR, byHashFile.get().getId()));
        }

        Document document = new Document(dto.getNameFile(),dto.getNameDocument(), hashCode);
        Optional<Integer> currentAuditor = getCurrentAuditor();
        currentAuditor.ifPresent(document::setUserId);
        Optional<User> byIdEquals = userRepo.findByIdEquals(currentAuditor.get());
        if(byIdEquals.isPresent()) {
            User user = byIdEquals.get();
            String author = user.getFirstName() + " " + user.getLastName() + " " + user.getPatronymic();
            document.setNameAuthor(author);
            document.setNameInspector("system");
        }

        String typeFile = FilenameUtils.getExtension(dto.getFile().getOriginalFilename());
        String fileName = createFileName(dto.getNameFile(), typeFile);


        fileStorage.saveFilt(dto.getFile(), fileName);

        if (dto.getGroupUid() != null) {
            Document document1 = documentRepository.findByGroupUid(dto.getGroupUid()).stream().findFirst().orElse(null);
            document.setGroupUid(dto.getGroupUid());
            document.setNameDocument(document1.getNameDocument());
            document.setIsActive(true);
            document1.setIsActive(false);
            documentRepository.save(document1);
        } else {
            document.setGroupUid(UUID.randomUUID());
        }
        document.setIsActive(false);
        document.setFilePath(fileName);

        documentRepository.save(document);
        result.setId(document.getId());



        AtomicReference<List<String>> linesFile = new AtomicReference<>();
        try {
            FileHandler fileHandler = fileHandlers.get(typeFile);
            linesFile.set(fileHandler.getLinesFile(dto.getFile()));
        } catch (IOException e) {
            return result;
        }

        if (!dto.getNormalizers().isEmpty()) {
            normalizersHandlers.keySet().forEach(s ->
                linesFile.set(normalizersHandlers.get(s).fileNormalization(linesFile.get())));
        } else {
            NormalizType.getBase().forEach(s ->
                    linesFile.set(normalizersHandlers.get(s).fileNormalization(linesFile.get())));
        }

        DocumentInfo documentInfo = verificationAlgorithm.searchForDuplicates(linesFile.get(), document, false);
        document.setPercentageOriginality(documentInfo.getPercentageOriginality());
        documentRepository.save(document);

        return result;
    }

    private String createFileName(String s, String type) {
        return s + "_" + LocalDate.now() + "." + type;
    }

    @Override
    public Boolean save(Long id) {
        Optional<DocumentInfo> byId = documentInfoMongoRepository.findById(id);
        if(!byId.isPresent()){
            return false;
        }
        Optional<Document> byId1 = documentRepository.findById(byId.get().getDocumentId());
        Optional<Integer> currentAuditor = getCurrentAuditor();
        Optional<User> byIdEquals = userRepo.findByIdEquals(currentAuditor.get());
        if(byId1.isPresent()){
            Document document = byId1.get();
            User user = byIdEquals.get();
            String inspector = user.getFirstName() + " " + user.getLastName() + " " + user.getPatronymic();
            document.setNameInspector(inspector);
        }
        List<DocumentShort> listHashs = byId.get().getListHashs();
        documentMongoRepository.saveAll(listHashs);
        return true;
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
