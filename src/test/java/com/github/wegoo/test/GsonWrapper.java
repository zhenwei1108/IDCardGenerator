package com.github.wegoo.test;

import com.github.wegoo.id.generator.JsonWrapper;
import com.google.gson.Gson;

public class GsonWrapper implements JsonWrapper {

  @Override
  public String parseStringFromObject(Object obj) {
    return new Gson().toJson(obj);
  }

  @Override
  public <T> T parseObjectFromString(String str, Class<T> clazz) {
    Gson gson = new Gson();
    return gson.fromJson(str, clazz);
  }
}
