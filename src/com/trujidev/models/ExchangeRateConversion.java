package com.trujidev.models;

public class ExchangeRateConversion {
  private String conversionResultString;
  private String sourceCurrencyCode;
  private String destinationCurrencyCode;
  private double conversionRate;
  private double convertedAmount;

  public ExchangeRateConversion(String conversionResultString, String sourceCurrencyCode, String destinationCurrencyCode, double conversionRate, double convertedAmount) {
    this.conversionResultString = conversionResultString;
    this.sourceCurrencyCode = sourceCurrencyCode;
    this.destinationCurrencyCode = destinationCurrencyCode;
    this.conversionRate = conversionRate;
    this.convertedAmount = convertedAmount;
  }

  public String getResult() {
    return conversionResultString;
  }

  public String getBase_code() {
    return sourceCurrencyCode;
  }

  public String getTarget_code() {
    return destinationCurrencyCode;
  }

  public double getConversion_rate() {
    return conversionRate;
  }

  public double getConversion_result() {
    return convertedAmount;
  }

  @Override
  public String toString() {
    return "-----------------------------" +
        "\nConversion from " + sourceCurrencyCode + " to " + destinationCurrencyCode + ":\n" +
        "Converted Amount: " + conversionResultString +
        "\nRate: " + conversionRate +
        "\nResult: " + convertedAmount;
  }
}
