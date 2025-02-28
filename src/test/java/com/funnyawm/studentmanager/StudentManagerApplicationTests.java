package com.funnyawm.studentmanager;

import com.funnyawm.studentmanager.model.Admin;
import com.funnyawm.studentmanager.model.Student;
import com.funnyawm.studentmanager.service.AdminService;
import com.funnyawm.studentmanager.service.StudentService;
import com.funnyawm.studentmanager.utils.AESUtils;
import com.funnyawm.studentmanager.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentManagerApplicationTests {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;

    @Test
        //单元测试：测试登录功能
    void testLogin() throws Exception {
        Admin admin = new Admin();
        admin.setName("admin");
        admin.setPassword(AESUtils.encrypt("admin"));
        Admin target = adminService.getAdminByName(admin.getName());
        if (target != null && target.getPassword().equals(admin.getPassword())) {
            admin.setId(target.getId());
            admin.setToken(JWTUtils.genToken(admin));
            System.out.println(admin.getToken());
        }
        if (!JWTUtils.verifyToken(admin.getToken())) {
            throw new RuntimeException();
        }
    }

    @Test
        //单元测试：测试学生查询功能
    void testGetStudent() {
        Student student = studentService.getStudentById(95003);
        System.out.println("ID:" + student.getId());
        System.out.println("Name:" + student.getName());
        System.out.println("Gender:" + student.getGender());
    }

    @Test
        //单元测试：测试添加学生功能
    void testAddStudent() {
        Student student = new Student();
        student.setName("test");
        student.setGender("男");
        student.setPhone("123456789");
        student.setMajor("软件工程");
        studentService.insertStudent(student);
    }

    @Test
        //单元测试：测试检查注入功能
    void testCheckStudent() {
        Student student = new Student();
        student.setId(95006);
        student.setName("test");
        student.setGender("男");
        student.setAddress("山西省临汾市");
        student.setPhone("123456789");
        student.setMajor("=");
        if (student.checkInjection()) {
            throw new Error("Test failed!");
        }
    }

    @Test
        //单元测试：测试检查空字段功能
    void testEmptyDetection() {
        Student student = new Student();
        student.setId(95006);
        student.setName("test");
        student.setGender("男");
        student.setAddress("山西省临汾市");
        student.setPhone(null);
        student.setMajor("软件工程");
        Student student2 = new Student();
        student.setId(95007);
        student.setName("test");
        student.setGender("男");
        student.setAddress("山西省临汾市");
        student.setPhone("");
        student.setMajor("软件工程");
        if (student.checkEmpty() || student2.checkEmpty()) {
            throw new Error("Test failed!");
        }
    }

    @Test
        //单元测试：测试管理员密码的AES加密与解密
    void testEncryption() throws Exception {
        Admin admin = new Admin();
        admin.setName("admin");
        admin.setPassword("admin");
        String encryptedText = AESUtils.encrypt(admin.getPassword());
        System.out.println(encryptedText);
        if (!(admin.getPassword().equals(AESUtils.decrypt(encryptedText)))) {
            throw new Error("Test failed!");
        }
        System.out.println(AESUtils.decrypt(encryptedText));
    }
}
