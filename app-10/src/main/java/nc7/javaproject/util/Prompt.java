package nc7.javaproject.util;

import java.util.Scanner;

public class Prompt {

  private static final Scanner scanner = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  public static int inputInt(String title) {
    while (true) {
      try {
        System.out.print(title);
        String input = scanner.nextLine();
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("유효한 숫자를 입력해주세요.");
      }
    }
  }

  public static void close() {
    scanner.close();
  }
}

