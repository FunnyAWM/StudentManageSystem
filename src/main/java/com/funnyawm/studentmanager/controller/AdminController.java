package com.funnyawm.studentmanager.controller;

import com.funnyawm.studentmanager.model.Admin;
import com.funnyawm.studentmanager.model.TokenWrapper;
import com.funnyawm.studentmanager.service.AdminService;
import com.funnyawm.studentmanager.utils.AESUtils;
import com.funnyawm.studentmanager.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin/register")
    public ResponseEntity<String> register(@RequestBody Admin admin) throws Exception {
        admin.setPassword(AESUtils.encrypt(admin.getPassword()));
        adminService.addAdmin(admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) throws Exception {
        admin.setPassword(AESUtils.encrypt(admin.getPassword()));
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
