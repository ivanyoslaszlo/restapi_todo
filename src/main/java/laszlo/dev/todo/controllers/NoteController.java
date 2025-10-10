package laszlo.dev.todo.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import laszlo.dev.todo.repository.NotesRepository;
import laszlo.dev.todo.service.NotesService;
import laszlo.dev.todo.service.UserService;

import jakarta.servlet.http.HttpSession;
import laszlo.dev.todo.repository.UserRepository;
import laszlo.dev.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class NoteController {


    @Autowired
    NotesService notesService;


    @PostMapping("/note")
    public ResponseEntity<?> jegyzet_keszites(@RequestBody Map<String, String> data, HttpSession session, HttpServletResponse response) {



        String note = data.get("note");
        return notesService.jegyzet_keszites(note, session);
    }


    @GetMapping("/note")
    public ResponseEntity<?> jegyzet_lekeres(HttpSession session) {

        return notesService.jegyzet_lekérés(session);
    }


    @DeleteMapping("/note")
    public ResponseEntity<?> jegyzet_torles_adatbazisbol(@RequestBody List<String> notes, HttpSession session) {

        return notesService.jegyezttörlés_adatbázisbol(session, notes);

    }
}
