package org.example.image.controller;

import jakarta.transaction.Transactional;
import org.example.image.model.User;
import org.example.image.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-profile-image")
    @Transactional
    public ResponseEntity<?> addProfileImage(@RequestParam MultipartFile file, Principal principal) {
        userService.addProfileImage(file, principal.getName());
        return ResponseEntity.ok("Image added");
    }

    @GetMapping("/get-user")
    @Transactional
    public ResponseEntity<?> getUser(Principal principal) throws Exception {
        return ResponseEntity.ok(userService.findByEmail(principal.getName()));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) throws Exception {
        if(userService.findByEmail(email).isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        userService.deleteUser(email);
        return ResponseEntity.ok("User deleted");
    }

//    @PostMapping("/add-wishlist/{id}")
//    public ResponseEntity<?> addToWishList(@PathVariable String id, Principal principal) {
//        userService.addToWishList(id, principal.getName());
//        return ResponseEntity.ok("Book added to wishlist");
//    }
//
//    @GetMapping("/get-wishlist")
//    public ResponseEntity<?> getWishList(Principal principal) throws Exception {
//        return ResponseEntity.ok(userService.getWishList(principal.getName()));
//    }
//
//    @DeleteMapping("/clear-wishlist")
//    public ResponseEntity<?> deleteUser(Principal principal) throws Exception {
//        if(userService.findByEmail(principal.getName()).isEmpty()) {
//            return ResponseEntity.badRequest().body("User not found");
//        }
//        userService.deleteWishList(principal.getName());
//        return ResponseEntity.ok("User deleted");
//    }
}