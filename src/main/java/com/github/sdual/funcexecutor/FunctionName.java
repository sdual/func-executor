package com.github.sdual.funcexecutor;

public class FunctionName {

  private final String name;
  private final String paramType;

  private FunctionName(String name, String paramType) {
    this.name = name;
    this.paramType = paramType;
  }

  public String getName() {
    return name;
  }

  public String getParamType() {
    return paramType;
  }

  public static FunctionName of(String funcName, String paramType) {
    return new FunctionName(funcName, paramType);
  }

}
