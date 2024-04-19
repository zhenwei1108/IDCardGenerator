package com.github.wegoo.id.generator;

public interface JsonWrapper {

  String parseStringFromObject(Object obj);

  //将json字符串转为对象
  <T> T parseObjectFromString(String str, Class<T> clazz);

}
