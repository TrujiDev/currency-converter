package com.trujidev.models;

public class ExchangeRateConversion {
  private String result;
  private String base_code;
  private String target_code;
  private double conversion_rate;

  public ExchangeRateConversion(String result, String base_code, String target_code, double conversion_rate) {
    this.result = result;
    this.base_code = base_code;
    this.target_code = target_code;
    this.conversion_rate = conversion_rate;
  }

  @Override
  public String toString() {
    return "-----------------------------" +
        "\nConversion from " + base_code + " to " + target_code + ":\n" +
        "Rate: " + conversion_rate;
  }
}
