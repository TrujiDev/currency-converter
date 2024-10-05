package com.trujidev.models;

import java.util.*;

public class ExchangeRate {
  private String result;
  private String base_code;
  private Map<String, Double> conversion_rates;

  public ExchangeRate(String result, String base_code, Map<String, Double> conversion_rates) {
    this.result = result;
    this.base_code = base_code;
    this.conversion_rates = conversion_rates;
  }

  @Override
  public String toString() {
    StringJoiner ratesJoiner = new StringJoiner(", ", "{", "}");

    for (Map.Entry<String, Double> entry : conversion_rates.entrySet()) {
      ratesJoiner.add("\"" + entry.getKey() + "\": " + entry.getValue());
    }

    return "{\n" +
        "  \"result\": \"" + result + "\",\n" +
        "  \"base_code\": \"" + base_code + "\",\n" +
        "  \"conversion_rates\": " + ratesJoiner + "\n" +
        "}";
  }
}
