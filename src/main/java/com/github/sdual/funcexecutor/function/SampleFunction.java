package com.github.sdual.funcexecutor.function;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SampleFunction {

  public static String addTenString(Integer input) {
    int result = input + 10;
    return String.valueOf(result);
  }

  public static Double transformDouble(String input) {
    input = input + ".0";
    return Double.valueOf(input);
  }

  public static List<String> split(Double input) {
    String str = String.valueOf(input);
    String[] result = str.split(Pattern.quote("."));
    return Arrays.asList(result);
  }

}
