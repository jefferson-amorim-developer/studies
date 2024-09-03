package guru.springframework;

import lombok.Data;

/**
 * Created by jt on 10/10/18.
 */
@Data
public class Greating {

  private static final String HELLO = "Hello";
  private static final String WORLD = "World";

  private String message;

  public String helloWorld() {
    return Greating.HELLO + " " + Greating.WORLD;
  }

  public String helloWorld(String name) {
    return Greating.HELLO + " " + name;
  }
}
