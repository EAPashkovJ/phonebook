package webClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PhonebookWebClient {
    public static void main(String[] args) {
        String list = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
             list = mapper.writeValueAsString(List.of("generate", "vasia", "pupkin", "12", "12", "Minsk"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8000/phonebook"))
                .POST(HttpRequest.BodyPublishers.ofString(list))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
