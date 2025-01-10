package com.funnyawm.studentmanager.model;

import lombok.*;

import java.io.Serializable;
import java.util.regex.Pattern;

@Data
public class Student implements Serializable {
    public int id;
    public String name;
    public String gender;
    public String address;
    public String phone;
    public String major;

    public boolean checkInjection() {
        Pattern pattern = Pattern.compile("=");
        //The following statement returns false if injection was detected
        return !(pattern.matcher(name).find()
                || pattern.matcher(gender).find()
                || pattern.matcher(address).find()
                || pattern.matcher(phone).find()
                || pattern.matcher(major).find());
    }
    public boolean checkEmpty() {
        //The following statement returns false if anything is empty
        return !(name == null || name.isEmpty()
                || gender == null || gender.isEmpty()
                || address == null || address.isEmpty()
                || phone == null || phone.isEmpty()
                || major == null || major.isEmpty());
    }
}
