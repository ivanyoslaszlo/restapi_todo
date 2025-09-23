package laszlo.dev.todo.controllers;

import laszlo.dev.todo.entities.Users;
import laszlo.dev.todo.service.EmailService;
import laszlo.dev.todo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class RegisterController {

    UserService userService;
    EmailService emailService;

    public RegisterController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService=emailService;

    }

    @PostMapping("/register")
    public String register_user(@RequestBody Users users) {

      if (userService.registerUser(users))
      {
          emailService.sendEmail(users.getEmail(),users.getUsername());
          return "siker";
      }else
      {
       return "foglalt";
      }




    }


}
