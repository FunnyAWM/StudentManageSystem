package com.funnyawm.studentmanager.mapper;

import com.funnyawm.studentmanager.model.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE username = '${name}'")
    Admin getAdminByName(String userName);

    @Select("SELECT * FROM admin WHERE username = '${name}' AND password = '${password}'")
    Admin getAdmin(Admin admin);

    @Insert("INSERT INTO admin(username, password) VALUES ('${name}', '${password}')")
    void addAdmin(Admin admin);

// --注释掉检查 START (2025/1/13 19:19):
//    @Delete("DELETE FROM admin WHERE id = ${id}")
//    void deleteAdminById(int id);
// --注释掉检查 STOP (2025/1/13 19:19)
}
