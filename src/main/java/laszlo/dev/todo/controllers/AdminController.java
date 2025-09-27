package laszlo.dev.todo.controllers;

import jakarta.servlet.http.HttpSession;
import laszlo.dev.todo.entities.Users;
import laszlo.dev.todo.repository.NotesRepository;
import laszlo.dev.todo.repository.UserRepository;
import laszlo.dev.todo.service.NotesService;
import laszlo.dev.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminController {

    NotesService notesService;
    NotesRepository notesRepository;
    UserRepository userRepository;
    UserService userService;

    public AdminController(NotesService notesService, NotesRepository notesRepository, UserRepository userRepository, UserService userService) {
        this.notesService = notesService;
        this.notesRepository = notesRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/user_notes")
    public ResponseEntity<?> user_details(HttpSession session) {

        if (!userRepository.is_admin(session)) {

            return ResponseEntity.status(403).body("Nem vagy admin nincs jogod lekérni!!");

        } else {

            List<Users> users = notesService.felhasznalok_jegyzetek_listazasa();

            return ResponseEntity.ok().body(users);

        }


    }


    @PostMapping("/ban")
    public ResponseEntity<?> userBan(@RequestBody Map<String, String> request, HttpSession session) {

        String isAdmin = (String) session.getAttribute("user");

        if (!isAdmin.equals("admin")) {
            return ResponseEntity.status(403).body("Hozzá férés megtagadva!");
        } else {

            String username = request.get("username");
            String action = request.get("action");
            userService.banuser(username, action);
            return  ResponseEntity.ok(username+" felhasználó bannolva");

        }


    }
}
