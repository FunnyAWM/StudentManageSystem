package com.funnyawm.studentmanager.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin implements Serializable {
    public int id;
    public String name;
    public String password;
    public String token;
}
