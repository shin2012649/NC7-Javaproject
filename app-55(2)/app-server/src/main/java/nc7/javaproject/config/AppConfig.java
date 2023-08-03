package nc7.javaproject.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import nc7.util.Bean;
import nc7.util.ComponentScan;
import nc7.util.SqlSessionFactoryProxy;

// Application을 실행하는데 필요한 객체를 설정하는 일을 한다.
//
@ComponentScan(basePackages={"nc7.javaproject.dao","nc7.javaproject.Handler"})
public class AppConfig {

  // Mybatis 객체 준비
  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    return new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("nc7/javaproject/config/mybatis-config.xml")));
  }
}
