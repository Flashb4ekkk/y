package org.example.image.service;

import jakarta.transaction.Transactional;
import org.example.image.dto.UserRegistration;
import org.example.image.model.Book;
import org.example.image.model.Role;
import org.example.image.model.User;
import org.example.image.repository.BookRepository;
import org.example.image.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public Optional<User> findByEmail(String username) throws Exception {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new Exception("User not found"));
        if(user.getImage() == null) {
            String imagePath = "src/main/resources/static/default-avatar.png";
            try {
                byte[] defaultImage = Files.readAllBytes(Paths.get(imagePath));
                user.setImage(defaultImage);
            } catch (IOException e) {
                throw new Exception("Error reading default image", e);
            }
        }
        return Optional.of(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User findByRefreshToken(String refreshToken) {
        return userRepository.findByRefreshToken(refreshToken);
    }

    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    public User createUser(UserRegistration userRegistration) {
        User user = User.builder()
                .username(userRegistration.getUsername())
                .email(userRegistration.getEmail())
                .firstName(userRegistration.getFirstName())
                .lastName(userRegistration.getLastName())
                .mobilePhone(passwordEncoder.encode(userRegistration.getPassword()))
                .password(passwordEncoder.encode(userRegistration.getPassword()))
                .buck(5L)
                .rating(7.0)
                .role(Role.ROLE_USER)
                .build();
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = findByEmailForCheck(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        User user = userOptional.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    public Optional<User> findByEmailForCheck(String email) {
        return userRepository.findByEmail(email);
    }

    public Long findIdByEmail(String email) {
        return userRepository.findIdByEmail(email);
    }

    public void addProfileImage(MultipartFile file, String name) {
        User user = userRepository.findByEmail(name).get();
        try {
            user.setImage(file.getBytes());
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void addToWishList(String id, String name) {
//        User user = userRepository.findByEmail(name).get();
//        Book book = bookRepository.findById(Long.parseLong(id)).get();
//        if(user.getWishList() == null) {
//            user.setWishList(new ArrayList<>());
//        }
//        user.getWishList().add(book);
//        userRepository.save(user);
//    }
//
//    public List<Book> getWishList(String name) {
//        User user = userRepository.findByEmail(name).get();
//        if(user.getWishList() == null) {
//            throw new RuntimeException("Wishlist is empty");
//        }
//        return user.getWishList();
//    }
//
//    public void deleteWishList(String name) {
//        User user = userRepository.findByEmail(name).get();
//        user.getWishList().clear();
//        userRepository.save(user);
//    }
}
