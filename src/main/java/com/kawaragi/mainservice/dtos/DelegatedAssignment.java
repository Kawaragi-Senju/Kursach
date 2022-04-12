package com.kawaragi.mainservice.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "delegateassignment")
@NoArgsConstructor
public class DelegatedAssignment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn(name = "assignmentid")
    @OneToOne
    private Assignment assignment;
    @JoinColumn(name = "changedassignee")
    @OneToOne
    private Worker newAssignee;

    public DelegatedAssignment(Assignment a, Worker w){
        this.assignment = a;
        this.newAssignee = w;
    }
}
