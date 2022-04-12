package com.kawaragi.mainservice.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "document2")
@NoArgsConstructor
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "doc")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;
    @Column(name = "assignmentid")
    private Long assignmentID;
    @Column(name = "filename")
    private String filename;
    @Column(name = "contenttype")
    private String contentType;
    public Document(byte[] data, Long assignmentID, String filename, String contentType){
        this.data = data;
        this.assignmentID = assignmentID;
        this.filename = filename;
        this.contentType=contentType;
    }
}
