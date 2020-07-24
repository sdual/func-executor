package com.github.sdual.funcexecutor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.github.sdual.funcexecutor.sandbox.ComposableFunction;
import com.github.sdual.funcexecutor.sandbox.FunctionComposer;
import com.github.sdual.funcexecutor.sandbox.FunctionName;
import com.github.sdual.funcexecutor.sandbox.SampleFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.junit.Test;

public class FunctionExecutorTest {

  @Test
  public void testExecuteFunctionByConfig() {
    List<FunctionName> functionNameList = new ArrayList<>();
    functionNameList.add(FunctionName.of("addAndToString", "java.lang.Integer"));
    functionNameList.add(FunctionName.of("transformToDouble", "java.lang.String"));
    functionNameList.add(FunctionName.of("splitWithDot", "java.lang.Double"));

    Function<Integer, List<String>> f1 = FunctionComposer.compose(functionNameList);
    List<String> actual = f1.apply(10);
    List<String> expected = new ArrayList<>();
    expected.add("20");
    expected.add("0");

    assertThat(actual, is(expected));
  }

  @Test
  public void testComposeFunction() {
    Function<Integer, List<String>> f2 =
            ComposableFunction.from(SampleFunction::addAndToString,
                    ComposableFunction.from(SampleFunction::transformToDouble,
                            ComposableFunction.from(SampleFunction::splitWithDot)));

    List<String> actual = f2.apply(10);
    List<String> expected = new ArrayList<>();
    expected.add("20");
    expected.add("0");

    assertThat(actual, is(expected));
  }

}
