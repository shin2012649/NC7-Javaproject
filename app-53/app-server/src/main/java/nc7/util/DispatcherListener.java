package nc7.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import nc7.javaproject.handler.BoardAddListener;
import nc7.javaproject.handler.BoardDeleteListener;
import nc7.javaproject.handler.BoardDetailListener;
import nc7.javaproject.handler.BoardListListener;
import nc7.javaproject.handler.BoardUpdateListener;
import nc7.javaproject.handler.LoginListener;
import nc7.javaproject.handler.ParticipantAddListener;
import nc7.javaproject.handler.ParticipantDeleteListener;
import nc7.javaproject.handler.ParticipantDetailListener;
import nc7.javaproject.handler.ParticipantListListener;
import nc7.javaproject.handler.ParticipantUpdateListener;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.dao.MySQLBoardDao;
import nc7.javaproject.dao.MySQLParticipantDao;
import nc7.javaproject.dao.ParticipantDao;

public class DispatcherListener implements ActionListener {

  // 객체 보관소
  Map<String,Object> beanContainer = new HashMap<>();

  public DispatcherListener() throws Exception {

    // Mybatis 준비
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("nc7/javaproject/config/mybatis-config.xml")));
    beanContainer.put("sqlSessionFactory", sqlSessionFactory);

    // DAO 준비
    ParticipantDao participantDao = new MySQLParticipantDao(sqlSessionFactory);
    BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
    beanContainer.put("memberDao", participantDao);
    beanContainer.put("boardDao", boardDao);

    // Listener 준비
    beanContainer.put("login", new LoginListener(participantDao));

    beanContainer.put("participant/add", new ParticipantAddListener(participantDao, sqlSessionFactory));
    beanContainer.put("participant/list", new ParticipantListListener(participantDao));
    beanContainer.put("participant/detail", new ParticipantDetailListener(participantDao));
    beanContainer.put("participant/update", new ParticipantUpdateListener(participantDao, sqlSessionFactory));
    beanContainer.put("participant/delete", new ParticipantDeleteListener(participantDao, sqlSessionFactory));

    beanContainer.put("board/add", new BoardAddListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/list", new BoardListListener(1, boardDao));
    beanContainer.put("board/detail", new BoardDetailListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/update", new BoardUpdateListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/delete", new BoardDeleteListener(1, boardDao, sqlSessionFactory));

    beanContainer.put("reading/add", new BoardAddListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("reading/list", new BoardListListener(2, boardDao));
    beanContainer.put("reading/detail", new BoardDetailListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("reading/update", new BoardUpdateListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("reading/delete", new BoardDeleteListener(2, boardDao, sqlSessionFactory));
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    ActionListener listener = (ActionListener) beanContainer.get(prompt.getAttribute("menuPath"));
    if (listener == null) {
      throw new RuntimeException("해당 요청을 처리할 수 없습니다.");
    }
    listener.service(prompt);
  }

  public Object getBean(String name) {
    return beanContainer.get(name);
  }
}