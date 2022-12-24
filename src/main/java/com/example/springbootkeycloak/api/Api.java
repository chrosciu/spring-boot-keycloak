package com.example.springbootkeycloak.api;

import javax.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Api {

    @RolesAllowed({"user", "admin"})
    @RequestMapping(value = "/readPost", method = RequestMethod.GET)
    public ResponseEntity<String> readPost() {
        return ResponseEntity.ok("USER ACCESS");
    }

    @RolesAllowed("admin")
    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public ResponseEntity<String> deletePost() {
        return ResponseEntity.ok("ADMIN ACCESS");
    }
}
