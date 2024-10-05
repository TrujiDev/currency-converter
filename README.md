# Currency Converter Application

## Description

This application allows users to convert between different currencies using real-time exchange rates. The application fetches exchange rates from an external API and displays the converted amount along with a history of conversions performed.

## Features

- **Currency Conversion**: Convert from one currency to another using live exchange rates.
- **Conversion History**: View a history of all conversions made during the session.
- **User-Friendly Interface**: Simple command-line interface for user interaction.

## Technologies Used

- Java
- Gson (for JSON parsing)
- Java HTTP Client
- DecimalFormat (for formatting decimal numbers)

## Setup Instructions

1. Clone the repository to your local machine.
2. Make sure you have Java installed on your machine.
3. Add the Gson library to your project (e.g., by adding the dependency to your `pom.xml` if you're using Maven).
4. Get an API key from [Exchange Rate API](https://www.exchangerate-api.com/) and replace the placeholder API key in the code.

## Execution

To run the application, follow these steps:

1. Open your terminal or command prompt.
2. Navigate to the directory where the application is located.
3. Compile the Java files using:
   ```bash
   javac App.java
   ```
4. Run the application using:
   ```bash
   java App
   ```

## Usage

Upon running the application, the user will see the welcome message and a menu with the following options:

1. **Convert currencies**: The user can input the source currency, destination currency, and amount to convert. The application will display the converted amount.
2. **View conversion history**: The user can see all previous conversions made in the current session.
3. **Exit**: The user can exit the application.

## Example

```
Welcome to the Currency Converter!

*** Currency Converter ***
1. Convert currencies
2. View conversion history
3. Exit
Please select an option (1-3): 1

--- Available Currencies ---
USD - US Dollar
ARS - Argentine Peso
BOB - Bolivian Boliviano
BRL - Brazilian Real
CLP - Chilean Peso
COP - Colombian Peso
-----------------------------
You can choose from the currencies above for conversion.

Enter the source currency code (e.g., USD): USD
Enter the destination currency code (e.g., COP): COP
Enter the amount to be converted: 100
Conversion Successful! The converted amount is: 3500000.00 COP
```
