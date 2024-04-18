package com.github.wegoo.id.generator;

public interface JsonWrapper {

  String parseStringFromObject(Object obj);

  <T> T parseObjectFromString(String str, Class<T> clazz);

}
