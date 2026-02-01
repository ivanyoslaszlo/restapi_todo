package laszlo.dev.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private Mylogger mylogger;
    String BREVO_API_KEY  = System.getenv("EMAIL_PASSWORD");
    private final String API_URL = "https://api.brevo.com/v3/smtp/email";

    @Async
    public void send_Email(String to, String subject, String text) {
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", BREVO_API_KEY);


        Map<String, Object> body = new HashMap<>();
        body.put("sender", Map.of("email", "lacitodo@gmail.com"));
        body.put("to", List.of(Map.of("email", to)));
        body.put("subject", subject);
        body.put("htmlContent", "<html><body><p>" + text + "</p></body></html>");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForEntity(API_URL, request, String.class);
            mylogger.info("Email elküldve (Brevo API): " + to);
        } catch (Exception e) {
            mylogger.warn("Brevo API hiba: " + e.getMessage());
        }
    }

    @Async
    public void sendRegistrationEmail(String to, String username) {
        send_Email(to, "Sikeres regisztráció", "Kedves " + username + "! Köszönjük a regisztrációt!");
    }

    @Async
    public void sendDeletedAccountEmail(String to, String username) {
        send_Email(to, "Fiók törölve", "Szia " + username + "! Sajnáljuk, hogy törölted a fiókod.");
    }
}