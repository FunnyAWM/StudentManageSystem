package com.funnyawm.studentmanager.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
    private int id;
    private String name;
    private String password;
    private String token;
}
