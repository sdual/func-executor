package com.github.sdual.funcexecutor.compose;

import java.util.Map;

public interface FunctionType<T> {

  T run();

  abstract class AbstractFunctionType<U> implements FunctionType<U> {
    private final String name;
    private final Map<ReturnType, FunctionType<U>> prevFunc;

    public AbstractFunctionType(String name, Map<ReturnType, FunctionType<U>> prevFuncs) {
      this.name = name;
      this.prevFunc = prevFuncs;
    }

    public String getName() {
      return name;
    }

    public Map<String, FunctionType<U>> getPrevFuncs() {
      return prevFunc;
    }
  }

  class Func1<V> extends AbstractFunctionType<V> {

    public Func1(String name, Map<String, FunctionType<V>> prevFunc) {
      super(name, prevFunc);
    }

    public V run() {
      return getPrevFuncs().run();
    }
  }

  class Func2<V> extends AbstractFunctionType<V> {

    public Func2(String name, Map<String, FunctionType<V>> prevFunc) {
      super(name, prevFunc);
    }

    public V run() {
      return getPrevFuncs().run();
    }
  }

  class Func3<V> extends AbstractFunctionType<V> {

    public Func3(String name, Map<String, FunctionType<V>> prevFunc) {
      super(name, prevFunc);
    }

    public V run() {
      return getPrevFuncs().run();
    }
  }

}
