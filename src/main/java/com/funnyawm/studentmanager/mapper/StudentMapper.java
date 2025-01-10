package com.funnyawm.studentmanager.mapper;

import com.funnyawm.studentmanager.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM student WHERE id = ${id}")
    Student getStudentById(int id);

    @Insert("INSERT INTO student(name, gender, address, phone, major) VALUES ('${name}', '${gender}', '${address}', '${phone}', '${major}')")
    void insertStudent(Student student);

    @Delete("DELETE FROM student WHERE id = ${id}")
    void deleteStudentById(int id);

    @Update("UPDATE student SET name='${name}',gender='${gender}',address='${address}',phone='${phone}',major='${major}' WHERE id=${id}")
    void updateStudent(Student student);

    @Select("SELECT * FROM student")
    List<Student> getAllStudents();
}
