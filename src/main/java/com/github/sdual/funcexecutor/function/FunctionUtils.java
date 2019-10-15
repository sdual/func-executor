package com.github.sdual.funcexecutor.function;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

public class FunctionUtils {

  public static <T, R> Function<T, R> toFunction(Method method) {
    return input -> {
      Object result;
      try {
        result = method.invoke(null, input);
      } catch (IllegalAccessException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
      return (R) result;
    };
  }

}
