package com.github.sdual.funcexecutor;

import com.sun.tools.javac.util.Pair;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
    Config conf = ConfigFactory.load();
    List<Pair<String, String>> functionNameList = extractFuncAndTypeName(conf);

    Function<Integer, List<String>> f2 = FunctionComposer.compose(functionNameList);

    System.out.println(f2.apply(10));
  }

  private static List<Pair<String, String>> extractFuncAndTypeName(Config conf) {
    ConfigList functionNameConf = conf.getList("functions");
    List<Pair<String, String>> functionNameList =
        functionNameConf.unwrapped().stream().map(nameObj -> (HashMap<String, String>) nameObj)
            .map(name -> Pair.of(name.get("name"), name.get("param_type")))
            .collect(Collectors.toList());
    return functionNameList;
  }

}
