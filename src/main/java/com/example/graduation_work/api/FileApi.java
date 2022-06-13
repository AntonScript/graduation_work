package com.example.graduation_work.api;

import com.example.graduation_work.api.dto.file.FileRequestDto;
import com.example.graduation_work.api.dto.file.FileResponseDto;
import com.example.graduation_work.api.dto.file.ZipDto;
import com.example.graduation_work.jpa.entity.Document;
import com.example.graduation_work.mongo.model.DocumentInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

/**
 * API для работы со файлами.
 *
 * @author Anton Mamakin
 */
@Api(description = "API для работы с файлами для проверки", tags = "file")
@RequestMapping(FileApi.PATH)
public interface FileApi {

    String PATH = "/api/rest/v1/file";

    @ApiOperation(value = "", notes = "",
                  response = FileResponseDto.class, tags = {"file"})
    @PostMapping(value = "/create",
                 produces = {APPLICATION_JSON_UTF8_VALUE},
                 consumes = {MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<FileResponseDto> create(@ModelAttribute FileRequestDto fileRequestDto);

    @ApiOperation(value = "", notes = "",
                  response = Void.class, tags = {"file"})
    @GetMapping(value = "/save",
                produces = {APPLICATION_JSON_UTF8_VALUE})
    ResponseEntity<Void> save(@RequestParam Long id);

    @ApiOperation(value = "", notes = "",
                  response = ZipDto.class, tags = {"file"})
    @PostMapping(value = "/zip",
                 produces = {"application/zip"},
                 consumes = {APPLICATION_JSON_UTF8_VALUE})
    ResponseEntity<ByteArrayResource> getFile(@RequestBody ZipDto dto);

    @ApiOperation(value = "", notes = "",
                  response = Document.class, responseContainer = "list", tags = {"file"})
    @GetMapping(value = "/all",
                 produces = {APPLICATION_JSON_UTF8_VALUE})
    ResponseEntity<List<Document>> getFiles(@RequestParam(required = false) boolean isSave,
                                            @RequestParam(required = false) Integer userId);

    @ApiOperation(value = "", notes = "",
                  response = DocumentInfo.class, responseContainer = "list", tags = {"file"})
    @GetMapping(value = "/all-info",
                produces = {APPLICATION_JSON_UTF8_VALUE})
    ResponseEntity<DocumentInfo> getFilesInfo(@RequestParam Long id);

}
