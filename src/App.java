import com.google.gson.*;

import com.trujidev.models.ExchangeRateConversion;

import java.io.IOException;
import java.net.*;
import java.net.http.*;
import java.util.*;

public class App {
  private static final Map<String, String> availableCurrencies = new HashMap<>();

  static {
    availableCurrencies.put("USD", "US Dollar");
    availableCurrencies.put("ARS", "Argentine Peso");
    availableCurrencies.put("BOB", "Bolivian Boliviano");
    availableCurrencies.put("BRL", "Brazilian Real");
    availableCurrencies.put("CLP", "Chilean Peso");
    availableCurrencies.put("COP", "Colombian Peso");
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setPrettyPrinting()
        .create();
    String apiKey = "cb0728d6206cede8d1068181";
    HttpClient client = HttpClient.newHttpClient();

    while (true) {
      System.out.println("\n--- Currency Converter Menu ---");
      System.out.println("1. Convert currencies");
      System.out.println("2. Exit");
      System.out.print("Select an option: ");
      int option = sc.nextInt();
      sc.nextLine();

      if (option == 1) {
        showAvailableCurrencies();

        System.out.print("Enter the source currency code (e.g., USD): ");
        String baseCurrency = sc.nextLine().toUpperCase();

        System.out.print("Enter the destination currency code (e.g., COP): ");
        String targetCurrency = sc.nextLine().toUpperCase();

        System.out.print("Enter the amount to be converted: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        String baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + baseCurrency + "/" + targetCurrency + "/" + amount;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUrl)).GET().build();

        try {
          HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
          if (response.statusCode() == 200) {
            ExchangeRateConversion apiResponse = gson.fromJson(response.body(), ExchangeRateConversion.class);
            System.out.println(apiResponse);
          } else {
            System.out.println("Error in the request: " + response.statusCode());
          }
        } catch (IOException | InterruptedException e) {
          System.out.println("Error when making the request: " + e.getMessage());
        }
      } else if (option == 2) {
        System.out.println("Exiting the program...");
        break;
      } else {
        System.out.println("Invalid option. Please select 1 or 2.");
      }
    }
  }

  private static void showAvailableCurrencies() {
    System.out.println("\n--- Available Currencies ---");
    for (Map.Entry<String, String> currency : availableCurrencies.entrySet()) {
      System.out.println(currency.getKey() + " - " + currency.getValue());
    }
    System.out.println("-----------------------------");
    System.out.println("You can choose from the currencies above for conversion.\n");
  }
}
