package nc7.mapp.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import nc7.mapp.dao.*;
import nc7.util.SqlSessionFactoryProxy;

@WebServlet(
    value="/init",
    loadOnStartup = 1
)
public class InitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static SqlSessionFactory sqlSessionFactory;
    public static MySQLEventDao eventDao;
    public static MySQLActorDao actorDao;
    public static MySQLCommentDao commentDao;
    public static MySQLCommentLikeDao commentLikeDao;
    public static MySQLCountryDao countryDao;
    public static MySQLEventApplicationDao eventApplicationDao;
    public static MySQLEventDao mySqlEventDao;
    public static MySQLFilmActorDao filmActorDao;
    public static MySQLFilmCountryDao filmCountryDao;
    public static MySQLFilmDao filmDao;
    public static MySQLFilmGenreDao filmGenreDao;
    public static MySQLFilmSrcDao filmSrcDao;
    public static MySQLFilmStaffDao filmStaffDao;
    public static MySQLGenreDao genreDao;
    public static MySQLGradeDao gradeDao;
    public static MySQLManagerDao managerDao;
    public static MySQLRatingDao ratingDao;
    public static MySQLRoleDao roleDao;
    public static MySQLSrcDao srcDao;
    public static MySQLStaffDao staffDao;
    public static MySQLUserDao userDao;
    public static MySQLWatchListDao watchListDao;

    @Override
    public void init() throws ServletException {
        System.out.println("InitServlet.init() 호출됨!");

        try {
            sqlSessionFactory = new SqlSessionFactoryProxy(
                new SqlSessionFactoryBuilder().build(
                    Resources.getResourceAsStream("nc7/mapp/config/mybatis-config.xml")));

            eventDao = new MySQLEventDao(sqlSessionFactory);
            actorDao = new MySQLActorDao(sqlSessionFactory);
            commentDao = new MySQLCommentDao(sqlSessionFactory);
            commentLikeDao = new MySQLCommentLikeDao(sqlSessionFactory);
            countryDao = new MySQLCountryDao(sqlSessionFactory);
            eventApplicationDao = new MySQLEventApplicationDao(sqlSessionFactory);
            mySqlEventDao = new MySQLEventDao(sqlSessionFactory);
            filmActorDao = new MySQLFilmActorDao(sqlSessionFactory);
            filmCountryDao = new MySQLFilmCountryDao(sqlSessionFactory);
            filmDao = new MySQLFilmDao(sqlSessionFactory);
            filmGenreDao = new MySQLFilmGenreDao(sqlSessionFactory);
            filmSrcDao = new MySQLFilmSrcDao(sqlSessionFactory);
            filmStaffDao = new MySQLFilmStaffDao(sqlSessionFactory);
            genreDao = new MySQLGenreDao(sqlSessionFactory);
            gradeDao = new MySQLGradeDao(sqlSessionFactory);
            managerDao = new MySQLManagerDao(sqlSessionFactory);
            ratingDao = new MySQLRatingDao(sqlSessionFactory);
            roleDao = new MySQLRoleDao(sqlSessionFactory);
            srcDao = new MySQLSrcDao(sqlSessionFactory);
            staffDao = new MySQLStaffDao(sqlSessionFactory);
            userDao = new MySQLUserDao(sqlSessionFactory);
            watchListDao = new MySQLWatchListDao(sqlSessionFactory);

        } catch (Exception e) {
            System.out.println("InitServlet.init() 실행 중 오류 발생!");
            e.printStackTrace();
        }
    }
}
