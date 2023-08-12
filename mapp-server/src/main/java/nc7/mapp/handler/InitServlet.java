package nc7.mapp.handler;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import nc7.mapp.dao.BoardDao;
import nc7.mapp.dao.MySQLEventDao;
import nc7.mapp.dao.MySQLBoardDao;
import nc7.mapp.dao.MySQLParticipantDao;
import nc7.mapp.dao.MySqlEventDao;
import nc7.mapp.dao.ParticipantDao;
import nc7.util.SqlSessionFactoryProxy;

@WebServlet(
    value="/init",
    loadOnStartup = 1
    )
public class InitServlet extends HttpServlet {

  
  private static final long serialVersionUID = 1L;
  public static SqlSessionFactory sqlSessionFactory;
  public static BoardDao boardDao;
  public static ParticipantDao participantDao;
  public static MySQLEventDao eventDao;
  
  
  @Override
  public void init() throws ServletException {
    System.out.println("InitServlet.init() 호출됨!");

    try {
      sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(
              Resources.getResourceAsStream("nc7/javaproject/config/mybatis-config.xml")));

      boardDao = new MySQLBoardDao(sqlSessionFactory);
      participantDao = new MySQLParticipantDao(sqlSessionFactory);
      eventDao = new MySqlEventDao(sqlSessionFactory);

    } catch (Exception e) {
      System.out.println("InitServlet.init() 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
