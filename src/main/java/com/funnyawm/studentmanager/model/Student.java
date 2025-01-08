package com.funnyawm.studentmanager.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class Student {
    public int id;
    public String name;
    public String gender;
    public String address;
    public String phone;
    public String major;

}
