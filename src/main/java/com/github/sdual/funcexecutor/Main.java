package com.github.sdual.funcexecutor;

import com.github.sdual.funcexecutor.function.ComposableFunction;
import com.github.sdual.funcexecutor.function.SampleFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

  public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {

    // 関数の合成
    Function<Integer, List<String>> f2 =
        ComposableFunction.from(SampleFunction::addAndToString,
            ComposableFunction.from(SampleFunction::transformToDouble,
                ComposableFunction.from(SampleFunction::splitWithDot)));

    System.out.println(f2.apply(10));

    // 設定ファイルから関数名をとってきて、動的に関数合成をする。
    // このような関数と引数の型の名前を設定ファイルからとってきたとする。
    List<FunctionName> functionNameList = new ArrayList<>();
    functionNameList.add(FunctionName.of("addAndToString", "java.lang.Integer"));
    functionNameList.add(FunctionName.of("transformToDouble", "java.lang.String"));
    functionNameList.add(FunctionName.of("splitWithDot", "java.lang.Double"));

    // 上の設定を使って動的に関数を合成する。
    Function<Integer, List<String>> f1 = FunctionComposer.compose(functionNameList);
    // 合成関数を実行
    System.out.println(f1.apply(10));

  }

}
