package com.github.sdual.funcexecutor.function;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

public class ComposableFunction<T, R, S> implements Function<T, S> {

  private final Function<R, S> nextFunc;
  private final Function<T, R> func;

  public ComposableFunction(Function<T, R> func, Function<R, S> nextFunc) {
    this.func = func;
    this.nextFunc = nextFunc;
  }

  @SuppressWarnings("unchecked")
  public ComposableFunction(Function<T, R> func) {
    this.func = func;
    this.nextFunc = (Function<R, S>) Function.identity();
  }

  @SuppressWarnings("unchecked")
  public ComposableFunction(Method method, Function<R, S> nextFunc) {
    this.func = toFunction(method);
    this.nextFunc = nextFunc;
  }

  @SuppressWarnings("unchecked")
  public ComposableFunction(Method method) {
    this(method, (Function<R, S>) Function.identity());
  }

  @Override
  public S apply(T t) {
    return nextFunc.compose(func).apply(t);
  }

  private Function<T, R> toFunction(Method method) {
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
