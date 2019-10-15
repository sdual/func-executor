package com.github.sdual.funcexecutor;

import com.github.sdual.funcexecutor.function.ComposableFunction;
import com.github.sdual.funcexecutor.function.SampleFunction;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;
import com.sun.tools.javac.util.Pair;

public class FunctionComposer {

  public static <T, R> Function<T, R> compose(List<Pair<String, String>> funcNames)
      throws ClassNotFoundException, NoSuchMethodException {

    Pair<String, String> funcName = funcNames.remove(0);
    Method method = SampleFunction.class.getMethod(funcName.fst, Class.forName(funcName.snd));
    if (funcNames.size() == 0) {
      return new ComposableFunction<>(method);
    }
    return new ComposableFunction<>(method, compose(funcNames));
  }

}
