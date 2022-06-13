package com.example.graduation_work.service.impl.storage;

import com.example.graduation_work.service.FileStorage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class FileStorageImpl implements FileStorage {

    private static final String SAVE_FOLDER = "/home/antonscript/Projects/GitLab/graduation_work/files/";

    /**
     * Расширения для zip-архива.
     */
    private static final String ZIP = ".zip";

    /**
     * Метод компрессии архива.
     */
    private static final int METHOD = ZipOutputStream.DEFLATED;

    /**
     * Уровень сжати архива.
     */
    private static final int LEVEL = 5;

    @Override
    public boolean saveFilt(MultipartFile file, String path) {
        try {
            Files.copy(file.getInputStream(), Paths.get(SAVE_FOLDER + path));
            return true;
        } catch (IOException e) {
            return false;

        }
    }

    @Override
    public String getZip(List<String> paths) {
        try {
            String name = createPatch();
            FileOutputStream fos = new FileOutputStream(name);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (String srcFile : paths) {
                File fileToZip = new File(SAVE_FOLDER + srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
            zipOut.close();
            fos.close();

            return name;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createPatch() {
        return SAVE_FOLDER + UUID.randomUUID() + ZIP;

    }

}
