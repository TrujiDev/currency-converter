package com.trujidev.models;

import java.util.Map;
import java.util.StringJoiner;

public class ExchangeRate {
  private String result;
  private String base_code;
  private Map<String, Double> conversion_rates;

  public ExchangeRate(String result, String base_code, Map<String, Double> conversion_rates) {
    this.result = result;
    this.base_code = base_code;
    this.conversion_rates = conversion_rates;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getBase_code() {
    return base_code;
  }

  public void setBase_code(String base_code) {
    this.base_code = base_code;
  }

  public Map<String, Double> getConversion_rates() {
    return conversion_rates;
  }

  public void setConversion_rates(Map<String, Double> conversion_rates) {
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
        "  \"conversion_rates\": " + ratesJoiner.toString() + "\n" +
        "}";
  }
}
