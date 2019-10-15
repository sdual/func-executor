package com.github.sdual.funcexecutor;

import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

  public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
    // このような関数と引数の名前を設定ファイルからとってきたとする。
    List<Pair<String, String>> functionNameList = new ArrayList<>();
    functionNameList.add(Pair.of("addTenString", "java.lang.Integer"));
    functionNameList.add(Pair.of("transformDouble", "java.lang.String"));
    functionNameList.add(Pair.of("split", "java.lang.Double"));

    // 設定の上から順番に関数を合成する。
    Function<Integer, List<String>> f2 = FunctionComposer.compose(functionNameList);

    // 合成関数を実行
    System.out.println(f2.apply(10));
  }

}
