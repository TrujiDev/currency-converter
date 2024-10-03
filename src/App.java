import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trujidev.models.ExchangeRate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
  public static void main(String[] args) {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setPrettyPrinting()
        .create();

    String apiKey = "";
    String baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest
        .newBuilder()
        .uri(URI.create(baseUrl))
        .GET()
        .build();

    try {
      HttpResponse<String> response = client
          .send(request, HttpResponse.BodyHandlers.ofString());

      if (response.statusCode() == 200) {
        ExchangeRate apiResponse = gson.fromJson(response.body(), ExchangeRate.class);
        System.out.println(apiResponse);
      } else {
        System.out.println("Error in the request: " + response.statusCode());
      }
    } catch (IOException | InterruptedException e) {
      System.out.println("Error when making the request: " + e.getMessage());
    }
  }
}
