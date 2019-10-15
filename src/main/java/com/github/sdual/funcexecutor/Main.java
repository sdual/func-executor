package com.github.sdual.funcexecutor;

import com.github.sdual.funcexecutor.function.ComposableFunction;
import com.github.sdual.funcexecutor.function.SampleFunction;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

public class Main {

  public static void main(String[] args) throws NoSuchMethodException {

//    Function<Integer, List<String>> f1 =
//        new ComposableFunction<>(SampleFunction::addTenString,
//            new ComposableFunction<>(SampleFunction::transformDouble,
//                new ComposableFunction<>(SampleFunction::split)));
//
//    System.out.println(f1.apply(10));

    Method method1 = SampleFunction.class.getMethod("addTenString", int.class);
    Method method2 = SampleFunction.class.getMethod("transformDouble", String.class);
    Method method3 = SampleFunction.class.getMethod("split", double.class);

    Function<Integer, List<String>> f2 =
        new ComposableFunction<>(method1,
            new ComposableFunction<>(method2,
                new ComposableFunction<>(method3)));

    System.out.println(f2.apply(10));
  }

}
