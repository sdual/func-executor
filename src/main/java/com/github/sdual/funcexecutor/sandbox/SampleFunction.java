package com.github.sdual.funcexecutor.sandbox;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SampleFunction {
  // なんでもよいテスト用の関数

  public static String addAndToString(Integer input) {
    int result = input + 10;
    return String.valueOf(result);
  }

  public static Double transformToDouble(String input) {
    input = input + ".0";
    return Double.valueOf(input);
  }

  public static List<String> splitWithDot(Double input) {
    String str = String.valueOf(input);
    String[] result = str.split(Pattern.quote("."));
    return Arrays.asList(result);
  }

}
