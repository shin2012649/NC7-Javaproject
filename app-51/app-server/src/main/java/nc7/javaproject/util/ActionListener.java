package nc7.javaproject.util;

import java.io.IOException;

public interface ActionListener {
  void service(BreadcrumbPrompt prompt) throws IOException;
}
