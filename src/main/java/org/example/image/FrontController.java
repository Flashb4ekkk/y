package org.example.image;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class FrontController {

    @GetMapping("/login/front")
    public String main() {
        return "main.html";
    }
}
