package com.github.sdual.funcexecutor.sandbox;

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
    this(func, (Function<R, S>) Function.identity());
  }

  @SuppressWarnings("unchecked")
  public ComposableFunction(Method method, Function<R, S> nextFunc) {
    this.func = FunctionUtils.toFunction(method);
    this.nextFunc = nextFunc;
  }

  @SuppressWarnings("unchecked")
  public ComposableFunction(Method method) {
    this(method, (Function<R, S>) Function.identity());
  }

  public static <T, R, S> ComposableFunction<T, R, S> from(Function<T, R> func) {
    return new ComposableFunction<>(func);
  }

  public static <T, R, S> ComposableFunction<T, R, S> from(Function<T, R> func,
      Function<R, S> nextFunc) {
    return new ComposableFunction<>(func, nextFunc);
  }

  @Override
  public S apply(T t) {
    return nextFunc.compose(func).apply(t);
  }

}
