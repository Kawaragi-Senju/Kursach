package com.kawaragi.mainservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    DIRECTOR_READ("director:read"),
    DIRECTOR_WRITE("director:write"),
    HEAD_READ("head:read"),
    HEAD_WRITE("head:write"),
    DEPWORKER_READ("worker:read"),
    DEPWORKER_WRITE("worker:write");

    private final String permission;
}
