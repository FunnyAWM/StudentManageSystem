package com.funnyawm.studentmanager.mapper;

import com.funnyawm.studentmanager.model.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE username = '${name}'")
    public Admin getAdminByName(String userName);
    @Select("SELECT * FROM admin WHERE username = '${name}' AND password = '${password}'")
    public Admin getAdmin(Admin admin);
    @Insert("INSERT INTO admin(username, password) VALUES ('${name}', '${password}')")
    public void addAdmin(Admin admin);
    @Delete("DELETE FROM admin WHERE id = ${id}")
    public void deleteAdminById(int id);
}
