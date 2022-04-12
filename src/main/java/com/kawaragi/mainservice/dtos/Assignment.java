package com.kawaragi.mainservice.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Data
@Entity(name = "assignment")
@RequiredArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long aid;

    @OneToOne
    @JoinColumn(name = "whocreated")
    private Worker whoCreated;

    @OneToOne
    @JoinColumn(name = "whoassignee")
    private Worker whoAssignee;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AssignmentStatus status;

    @Column(name="isdelegated")
    private boolean isDelegated;


    public Assignment(Worker whoCreated, Worker whoAssignee){
        this.whoCreated = whoCreated;
        this.whoAssignee = whoAssignee;
        this.status = AssignmentStatus.active;
    }
}
