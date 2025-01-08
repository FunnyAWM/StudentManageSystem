package com.funnyawm.studentmanager.service;

import com.funnyawm.studentmanager.mapper.AdminMapper;
import com.funnyawm.studentmanager.model.Admin;
import com.funnyawm.studentmanager.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminMapper {
    AdminMapper adminMapper;

    @Autowired
    public AdminService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin getAdminByName(String userName) {
        return adminMapper.getAdminByName(userName);
    }

    @Override
    public Admin getAdmin(Admin admin) {
        return adminMapper.getAdmin(admin);
    }

    @Override
    public void addAdmin(Admin admin) {
        adminMapper.addAdmin(admin);
    }

    @Override
    public void deleteAdminById(int id) {
        adminMapper.deleteAdminById(id);
    }

    public boolean verifyAdmin(String token) {
        return JWTUtils.verifyToken(token);
    }
}
