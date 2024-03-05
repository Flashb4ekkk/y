package org.example.image.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/add-buck/{bucks}")
    @Transactional
    public ResponseEntity<?> addBucksToUser(Principal principal, @PathVariable Long bucks) {
        userService.addBucksToUser(principal.getName(), bucks);
        return ResponseEntity.ok("bucks added");
    }
}