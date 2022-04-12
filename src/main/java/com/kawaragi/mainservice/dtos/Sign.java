package com.kawaragi.mainservice.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "sign")
@NoArgsConstructor
public class Sign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "directorsigned")
    private boolean isDirectorSigned;
    @Column(name = "headsigned")
    private boolean isHeadSigned;
    @Column(name = "workersigned")
    private boolean isWorkerSigned;
    @JoinColumn(name = "documentid")
    @OneToOne
    private Document document;
    @JoinColumn(name = "assignentid")
    @OneToOne
    private Assignment assignment;
    public Sign(Document doc, Assignment ass){
        this.document=doc;
        this.assignment=ass;
    }
}
