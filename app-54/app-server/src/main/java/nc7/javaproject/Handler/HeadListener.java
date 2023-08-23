package nc7.javaproject.handler;

import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;

public class HeadListener implements ActionListener{

  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("=============================[오호]==");
  }
}
