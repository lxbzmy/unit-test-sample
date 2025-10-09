package com.foo;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
import java.util.Stack;

import org.junit.Test;

public class StackTest {

  Stack<Object> stack = new Stack<>();

  @Test
  public void 空堆栈的size等于0() throws Exception {
    assertEquals(0, stack.size());
  }

  @Test
  public void 放入一个对象后size加1() throws Exception {
    stack.push("🐶");
    assertEquals(1, stack.size());
  }

  @Test
  public void pop一个对象后size减1() throws Exception {
    //TODO
  }

  @Test(expected = EmptyStackException.class)
  public void 空栈pop报异常() throws Exception {
    stack.pop();
//还有一种笨办法    
//    try{
//      stack.pop();
//      throw  new RuntimeException("没通过");
//    }catch (EmptyStackException e){
//      //通过了
//    }
  }
}
