package nc7.javaproject.listener;

import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.dao.MySQLBoardDao;
import nc7.javaproject.dao.MySQLUserDao;
import nc7.javaproject.dao.UserDao;
import nc7.util.NcpConfig;
import nc7.util.NcpObjectStorageService;
import nc7.util.SqlSessionFactoryProxy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// 웹애플리케이션 실행에 필요한 설정이나 객체를 준비한다.
// 언제? 웹애플리케이션 시작될 때!
//
@WebListener
public class ContextLoaderListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {

    // 준비한 객체를 담을 수 있도록 보관소를 꺼낸다.
    ServletContext ctx = sce.getServletContext();

    // 서블릿들이 공통으로 사용할 객체를 준비한다.
    try {
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
              new SqlSessionFactoryBuilder().build(
                      Resources.getResourceAsStream(ctx.getInitParameter("mybatis-config"))));

      BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
      UserDao userDao = new MySQLUserDao(sqlSessionFactory);
      NcpObjectStorageService ncpObjectStorageService = new NcpObjectStorageService(new NcpConfig());

      // 준비한 객체를 꺼내 쓸 수 있도록 보관소에 저장한다.
      ctx.setAttribute("sqlSessionFactory", sqlSessionFactory);
      ctx.setAttribute("boardDao", boardDao);
      ctx.setAttribute("userDao", userDao);
      ctx.setAttribute("ncpObjectStorageService", ncpObjectStorageService);

      System.out.println("ContextLoaderListener.contextInitialized() - 공통 객체 준비 완료!");

    } catch (Exception e) {
      System.out.println("ContextLoaderListener.contextInitialized() - 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
