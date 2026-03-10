package org.vtinstitute.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.vtinstitute.utils.HttpResponse;

import java.io.IOException;

import org.vtinstitute.controller.LogsController;
import org.vtinstitute.models.entity.Cours;
import org.vtinstitute.models.entity.Enrollment;
import org.vtinstitute.models.entity.Score;
import org.vtinstitute.models.entity.Student;
import org.vtinstitute.utils.RestApiConnection;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiManager {

    private LogsController logsController = new LogsController();
    private RestApiConnection restApiConnection = new RestApiConnection("http://localhost:8080/", "api/");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Student getStudent(String idcard) throws IOException {
        JavaType type = objectMapper.getTypeFactory().constructType(Student.class);
        HttpResponse response = restApiConnection.getRequest("students", idcard);
        return objectMapper.convertValue(response, type);
    }

    public Cours getCours(int idcours) throws IOException {
        HttpResponse response = restApiConnection.getRequest("courses", String.valueOf(idcours));
        String jsonResponse = (response.getBody() != null) ? response.getBody().toString() : "";
        System.out.println("DEBUG: El servidor respondió con: '" + jsonResponse + "'");

        if (jsonResponse.isEmpty()) {
            System.err.println("Error: El servidor devolvió una respuesta vacía para el curso: " + idcours);
            return null;
        }

        JavaType type = objectMapper.getTypeFactory().constructType(Cours.class);
        return objectMapper.readValue(jsonResponse, type);
    }

    public List<Score> getStudentExpedient(String idcard, String cours) throws IOException {
        String params = "?idcard=" + idcard + "&cours=" + cours;
        HttpResponse response = restApiConnection.getRequest("students/expedient", params);
        String jsonResponse = response.getBody().toString(); 

        if (jsonResponse == null || !jsonResponse.trim().startsWith("[")) {
            System.err.println("Error: La API devolvió un texto en lugar de una lista: " + jsonResponse);
            return new ArrayList<>(); 
        }

        JavaType type = objectMapper.getTypeFactory()
            .constructCollectionType(List.class, Score.class);

        return objectMapper.readValue(jsonResponse, type);
    }

    public List<Enrollment> getEnrollmentsByStudentCours(String idcard, int cours) throws IOException {
        String params = "?idcard=" + idcard + "&idcours=" + String.valueOf(cours);  
        JavaType type = objectMapper.getTypeFactory()
            .constructCollectionType(List.class, Enrollment.class);
        HttpResponse response = restApiConnection
            .getRequest("enrollments", params);

        String responseBody = response.getBody().toString();
        System.out.println(response.getStatusCode());

        if (responseBody == null || responseBody.isBlank()) {
            return Collections.emptyList();
        }

        return objectMapper.readValue(responseBody, type);
    }

    public int addStudentAPI(Student student) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(student);
        } catch(Exception e) {
            System.err.println("The data isn't imported to JSON correctly");
            logsController.logError(e.getMessage());
        }
        HttpResponse response = restApiConnection.post("students/add", json);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusMessage());
        return response.getStatusCode();
    }

    public void enrollStudentApi(Enrollment newEnrollment) throws IOException {
        // 1. Convertimos el objeto a JSON (Limpio y sin errores de concatenación)
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(newEnrollment);

        // 2. Hacemos el POST enviando el JSON en el cuerpo
        HttpResponse httpResponse = restApiConnection.post("/enrollments/enroll", jsonBody);
        
        if (httpResponse.getStatusCode() == 200 || httpResponse.getStatusCode() == 201) { // Suponiendo que tienes este helper
            System.out.println("Student enrolled successfully");
        } else {
            System.err.println("Error " + httpResponse.getStatusCode() + ": " + httpResponse.getBody());
        }
    }

    public void qualifyApi(Map<String, Object> newScore) throws IOException {
        String jsonPayload = objectMapper.writeValueAsString(newScore);        
        HttpResponse httpResponse = restApiConnection.put("scores/qualify", jsonPayload);

        if (httpResponse.getStatusCode() == 200) {
            System.out.println("✅ The score was updated successfully using PUT!");
        } else {
            System.err.println("❌ Error: " + httpResponse.getStatusCode());
            System.err.println("Detalle: " + httpResponse.getBody());
        }
    }
}
