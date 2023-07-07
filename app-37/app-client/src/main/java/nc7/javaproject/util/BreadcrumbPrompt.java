package nc7.javaproject.util;

import java.util.Stack;

public class BreadcrumbPrompt extends Prompt {

  private Stack<String> breadcrumbs = new Stack<>();

  public void appendBreadcrumb(String title) {
    this.breadcrumbs.push(title);
  }

  public void removeBreadcrumb() {
    this.breadcrumbs.pop();
  }

  public String inputMenu() {
    StringBuilder titleBuilder = new StringBuilder(); // 예) 메인/회원>
    for (String element : this.breadcrumbs) {
      if (titleBuilder.length() > 0) {
        titleBuilder.append("/");
      }
      titleBuilder.append(element);
    }
    titleBuilder.append("> ");
    return this.inputString(titleBuilder.toString());
  }
}
