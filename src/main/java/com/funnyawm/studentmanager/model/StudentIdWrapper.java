package com.funnyawm.studentmanager.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StudentIdWrapper {

    public void setId(int id) {
        this.id = id;
    }

    Integer id;

}
