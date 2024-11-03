package com.example.swh_back.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/* 
@RestController
@RequestMapping("/api/adminnn")
public class ViewController {

    @Autowired
    private ViewDao viewDao;

    @GetMapping("/usersss")
    public ResponseEntity<List<ViewUserAdmin>> getAllUsers() {
        List<ViewUserAdmin> users = viewDao.getAllUsers();
        
        return ResponseEntity.ok(users);
    }
}*/

@RestController
@RequestMapping("/api/adminnn")
public class ViewController {

    @Autowired
    private ViewDao viewDao;

    @GetMapping("/usersss")
    public List<ViewUserAdmin> getAllUsers(@RequestParam(value = "role", required = false) String role) {
        return viewDao.getAllUsers(role);
    }
}

