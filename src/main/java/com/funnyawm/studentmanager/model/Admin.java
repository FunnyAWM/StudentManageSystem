package com.funnyawm.studentmanager.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
    public int id;
    public String name;
    public String password;
    public String token;
}
