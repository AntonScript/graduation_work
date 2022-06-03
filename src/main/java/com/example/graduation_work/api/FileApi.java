package com.example.graduation_work.api;

import com.example.graduation_work.api.dto.FileRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation(value = "", notes = "Загрузить файл для проверки и получить id файла, для возможности проверки статусу",
                  response = Void.class, tags = {"tsr"})
    @PostMapping(value = "/create",
                 produces = {APPLICATION_JSON_UTF8_VALUE},
                 consumes = {MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<Long> create(@ApiParam(value = "file") @RequestPart("file") MultipartFile file,
                                @RequestBody FileRequestDto dto);

}
