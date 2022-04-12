package com.kawaragi.mainservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "worker")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Eager
public class Worker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private long id;
    @Column(name = "uname")
    private String name;
    @Column(name = "usurname")
    private String surname;
    @Column(name = "uemail")
    private String email;
    @Column(name = "upassword")
    private String password;
    @Column(name = "uposition", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Position position;

}
