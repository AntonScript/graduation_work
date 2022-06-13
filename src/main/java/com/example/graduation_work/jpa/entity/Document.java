package com.example.graduation_work.jpa.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "document")
@Data
public class Document {

    public Document() {
    }

    public Document(String nameFile, String nameDocument, String hashFile) {
        this.nameFile = nameFile;
        this.nameDocument = nameDocument;
        this.hashFile = hashFile;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name_file")
    private String nameFile;

    @Column(name = "name_document")
    private String nameDocument;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "name_author")
    private String nameAuthor;

    @Column(name = "name_inspector")
    private String nameInspector;

    @Column(name = "id_file")
    private Long idFileInStorage;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "hash_file")
    private String hashFile;

    @Column(name = "percentage_originality")
    private Double percentageOriginality;

    @Column(name = "group_uid")
    private UUID groupUid;

}
