package laszlo.dev.todo;

import laszlo.dev.todo.entities.Users;
import laszlo.dev.todo.repository.NotesRepository;
import laszlo.dev.todo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotesRepository notesRepository;

    /*@Test
    void user_delete(){
        boolean result= userRepository.delete_users("Misi");
        assertTrue(result,"Felhasználó törölve");
    }

     */

/*
    @Test
    void id() {
        int id = userRepository.get_userID("laci");
        System.out.println("Id:" + id);
        assertTrue(id>0);
    }


 */
    /*
    @Test
    void create_notes(){

        boolean result= notesRepository.createNote("detti","proba1");
        assertTrue(result,"Hozzá adás sikertelen!");
    }

     */
@Test
void testGetNotes() {

    String username = "laci";
    notesRepository.createNote(username, "hetedik jegyzet");
    notesRepository.createNote(username, "nyolcadik jegyzet");


    List<String> notes = notesRepository.getNotes(username);


    assertNotNull(notes);

    assertTrue(notes.contains("hetedik jegyzet"));
    assertTrue(notes.contains("nyolcadik jegyzet"));


}


}



