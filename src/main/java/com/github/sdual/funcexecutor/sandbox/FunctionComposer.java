package com.github.sdual.funcexecutor.sandbox;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

public class FunctionComposer {

  public static <T, R> Function<T, R> compose(List<FunctionName> funcNames) {

    FunctionName funcName = funcNames.remove(0);
    // reflectionを使って関数と型名から関数をとってくる
    Method method = null;
    try {
      method = SampleFunction.class.getMethod(funcName.getName(), Class.forName(funcName.getParamType()));
    } catch (ClassNotFoundException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
    if (funcNames.size() == 0) {
      return new ComposableFunction<>(method);
    }
    return new ComposableFunction<>(method, compose(funcNames));
  }

}
