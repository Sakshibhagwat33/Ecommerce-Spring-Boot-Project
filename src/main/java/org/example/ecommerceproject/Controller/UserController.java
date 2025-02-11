package org.example.ecommerceproject.Controller;

import org.example.ecommerceproject.Entity.User;
import org.example.ecommerceproject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @GetMapping("/")
   public String homePage(){
       return "home";
   }

   @GetMapping("/login")
   public String loginPage(){
       return "LOGIN";
   }

   @Autowired
   private UserRepository userRepository;


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "home"; // Redirect to home page on successful login
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "redirect:/"; // Redirect back to login page on failure
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {

        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists");
            return "register"; // Redirect back to registration page on failure
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return "LOGIN"; // Redirect to login page after successful registration
    }

    @GetMapping("/reg")
    public String res(){
        return "register";
    }

}


