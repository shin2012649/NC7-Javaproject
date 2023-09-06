package nc7.javaproject.config;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
        basePackages = {
                "nc7.javaproject.config",
                "nc7.javaproject.service"}
)
public class RootConfig {
    {
        System.out.println("RootConfig() 호출됨!");
    }
}
