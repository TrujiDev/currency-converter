import com.google.gson.*;
import com.trujidev.models.ExchangeRateConversion;

import java.io.IOException;
import java.net.*;
import java.net.http.*;
import java.text.DecimalFormat;
import java.util.*;

public class App {
  // Mapa que almacena las opciones de divisas disponibles
  private static final Map<String, String> currencyOptions = new HashMap<>();
  // Lista que guarda el historial de conversiones realizadas
  private static final List<String> conversionLog = new ArrayList<>();

  // Inicializa las divisas disponibles
  static {
    currencyOptions.put("USD", "US Dollar");
    currencyOptions.put("ARS", "Argentine Peso");
    currencyOptions.put("BOB", "Bolivian Boliviano");
    currencyOptions.put("BRL", "Brazilian Real");
    currencyOptions.put("CLP", "Chilean Peso");
    currencyOptions.put("COP", "Colombian Peso");
  }

  public static void main(String[] args) {
    // Scanner para obtener la entrada del usuario
    Scanner scanner = new Scanner(System.in);
    // Instancia de Gson para manejar la conversión de JSON
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) // Configura el formato de los nombres de los campos
        .setPrettyPrinting() // Habilita el formato legible del JSON
        .create();
    // Clave de la API para acceder al servicio de conversión
    String apiKey = "";

    System.out.println("Welcome to the Currency Converter!");

    // Crea un cliente HTTP
    try (HttpClient client = HttpClient.newHttpClient()) {
      // Formato decimal para mostrar resultados con dos decimales
      DecimalFormat decimalFormat = new DecimalFormat("#.00");

      // Ciclo principal para manejar las opciones del usuario
      while (true) {
        // Muestra el menú de opciones al usuario
        System.out.println("\n*** Currency Converter ***");
        System.out.println("1. Convert currencies");
        System.out.println("2. View conversion history");
        System.out.println("3. Exit");
        System.out.print("Please select an option (1-3): ");
        int userOption = scanner.nextInt(); // Captura la opción seleccionada por el usuario
        scanner.nextLine(); // Limpia el buffer de entrada

        // Si el usuario elige convertir divisas
        if (userOption == 1) {
          showAvailableCurrencies(); // Muestra las divisas disponibles

          // Solicita al usuario los códigos de las divisas
          System.out.print("Enter the source currency code (e.g., USD): ");
          String sourceCurrency = scanner.nextLine().toUpperCase(); // Convierte el código a mayúsculas

          System.out.print("Enter the destination currency code (e.g., COP): ");
          String destinationCurrency = scanner.nextLine().toUpperCase(); // Convierte el código a mayúsculas

          System.out.print("Enter the amount to be converted: ");
          double amountToConvert = scanner.nextDouble(); // Captura el monto a convertir
          scanner.nextLine(); // Limpia el buffer de entrada

          // Construye la URL de la API para la conversión
          String baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + sourceCurrency + "/" + destinationCurrency + "/" + amountToConvert;
          HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUrl)).GET().build(); // Crea la solicitud HTTP

          try {
            // Envía la solicitud y obtiene la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) { // Si la solicitud fue exitosa
              // Convierte la respuesta JSON a un objeto ExchangeRateConversion
              ExchangeRateConversion apiResponse = gson.fromJson(response.body(), ExchangeRateConversion.class);
              // Calcula el resultado de la conversión
              apiResponse = new ExchangeRateConversion(
                  apiResponse.getResult(), // Resultado de la conversión
                  apiResponse.getBase_code(), // Código de la divisa base
                  apiResponse.getTarget_code(), // Código de la divisa objetivo
                  apiResponse.getConversion_rate(), // Tasa de conversión
                  amountToConvert * apiResponse.getConversion_rate() // Monto convertido
              );

              // Formatea el resultado de la conversión y lo muestra al usuario
              String formattedConversionResult = decimalFormat.format(apiResponse.getConversion_result());
              System.out.println("Conversion Successful! The converted amount is: " + formattedConversionResult + " " + destinationCurrency);
              // Agrega el registro de conversión al historial
              conversionLog.add("Converted " + amountToConvert + " " + sourceCurrency + " to " + formattedConversionResult + " " + destinationCurrency);
            } else {
              // Muestra un mensaje de error si la solicitud falla
              System.out.println("Oops! There was an error with the request. Status Code: " + response.statusCode());
            }
          } catch (IOException | InterruptedException e) {
            // Maneja excepciones al enviar la solicitud
            System.out.println("Error when making the request: " + e.getMessage());
          }
        }
        // Si el usuario elige ver el historial de conversiones
        else if (userOption == 2) {
          viewConversionHistory(); // Muestra el historial de conversiones
        }
        // Si el usuario elige salir de la aplicación
        else if (userOption == 3) {
          System.out.println("Thank you for using the Currency Converter. Goodbye!");
          break; // Sale del bucle y finaliza la aplicación
        }
        // Si la opción seleccionada es inválida
        else {
          System.out.println("Invalid option. Please select 1, 2, or 3.");
        }
      }
    }
  }

  // Muestra las divisas disponibles para la conversión
  private static void showAvailableCurrencies() {
    System.out.println("\n--- Available Currencies ---");
    for (Map.Entry<String, String> currency : currencyOptions.entrySet()) {
      System.out.println(currency.getKey() + " - " + currency.getValue()); // Muestra el código y el nombre de la divisa
    }
    System.out.println("-----------------------------");
    System.out.println("You can choose from the currencies above for conversion.\n");
  }

  // Muestra el historial de conversiones realizadas
  private static void viewConversionHistory() {
    System.out.println("\n--- Conversion History ---");
    if (conversionLog.isEmpty()) { // Verifica si hay registros de conversión
      System.out.println("No conversion history found. Start converting to add records!");
    } else {
      // Muestra cada registro de conversión en el historial
      for (String record : conversionLog) {
        System.out.println(record);
      }
    }
    System.out.println("-----------------------------");
  }
}
