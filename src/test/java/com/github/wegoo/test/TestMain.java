package com.github.wegoo.test;

import com.github.wegoo.id.generator.IDGenerator;

public class TestMain {

  public static void main(String[] args) throws  Exception {
    GsonWrapper gsonWrapper = new GsonWrapper();
    IDGenerator idGenerator = new IDGenerator(gsonWrapper);
    for (int i = 0; i < 20; i++) {
      String randomPrefix = idGenerator.getRandomPrefix( gsonWrapper);
      String randomBirth = idGenerator.getRandomBirth();
      String suffix = idGenerator.getSuffix();
      String id = idGenerator.getID(randomPrefix + randomBirth + suffix);
      System.out.println(id);

    }

  }

}
