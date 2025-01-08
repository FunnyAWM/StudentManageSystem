package com.funnyawm.studentmanager.controller;

import com.funnyawm.studentmanager.model.Admin;
import com.funnyawm.studentmanager.model.TokenWrapper;
import com.funnyawm.studentmanager.service.AdminService;
import com.funnyawm.studentmanager.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin/register")
    public void register(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        Admin target = adminService.getAdmin(admin);
        if (target != null) {
            admin.setToken(JWTUtils.genToken(admin));
            return new ResponseEntity<>(admin.getToken(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/token/verify")
    public ResponseEntity<String> verifyToken(@RequestBody TokenWrapper token) {
        if (JWTUtils.verifyToken(token.getToken())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
