package nc7.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.util.DataSource;
import nc7.javaproject.vo.Board;
import nc7.javaproject.vo.Participant;

public class MysqlBoardDao implements BoardDao {

  DataSource ds;
  int category;

  public  MysqlBoardDao(DataSource ds, int category) {
    this.ds= ds;
    this.category = category;
  }

  @Override
  public void insert(Board board) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "insert into JavaProject_board(title,content,writer,password,category)"
            + " values(?,?,?,sha1(?),?)")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getWriter().getNo());
      stmt.setString(4, board.getPassword());
      stmt.setInt(5, this.category);

      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  /*
   select
     b.board_no,
     b.title,
     b.view_count,
     b.created_date,
     m.member_no,
     m.name
   from
     myapp_board b inner join myapp_member m on b.writer=m.member_no
   where
     category=1
   order by
     board_no desc
   */
  @Override
  public List<Board> list() {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "select" +
            "  b.board_no, " +
            "  b.title, " +
            "  b.view_count, " +
            "  b.created_date, " +
            "  p.participant_no, " +
            "  p.name " +
            " from " +
            "  JavaProject_board b inner join JavaProject_participant p on b.writer=p.participant_no" +
            " where " +
            "  category=?" +
            " order by " +
            "  board_no desc"
        )) {

      stmt.setInt(1, this.category);

      try (ResultSet rs = stmt.executeQuery()) {
        List<Board> list = new ArrayList<>();
        while (rs.next()) {
          Board b = new Board();
          b.setNo(rs.getInt("board_no"));
          b.setTitle(rs.getString("title"));
          b.setViewCount(rs.getInt("view_count"));
          b.setCreatedDate(rs.getTimestamp("created_date"));

          Participant writer = new Participant();
          writer.setNo(rs.getInt("participant_no"));
          writer.setName(rs.getString("name"));
          b.setWriter(writer);

          list.add(b);
        }
        return list;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public Board findBy(int no) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "select" +
            "  b.board_no, " +
            "  b.title, " +
            "  b.content," +
            "  b.view_count, " +
            "  b.created_date, " +
            "  p.particitpant_no, " +
            "  p.name " +
            " from " +
            "  JavaProject_board b inner join JavaProject_participant p on b.writer=p.participant_no" +
            " where " +
            "  category=?" +
            "  and board_no=?"
        )) {

      stmt.setInt(1, this.category);
      stmt.setInt(2, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Board b = new Board();
          b.setNo(rs.getInt("board_no"));
          b.setTitle(rs.getString("title"));
          b.setContent(rs.getString("content"));
          b.setViewCount(rs.getInt("view_count"));
          b.setCreatedDate(rs.getTimestamp("created_date"));

          Participant writer = new Participant();
          writer.setNo(rs.getInt("participant_no"));
          writer.setName(rs.getString("name"));
          b.setWriter(writer);

          stmt.executeUpdate("update JavaProject_board set"
              + " view_count=view_count + 1"
              + " where board_no=" + no);

          return b;
        }
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Board board) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "update JavaProject_board set"
            + " title=?,"
            + " content=?"
            + " where category=? and board_no=? and password=sha1(?)")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, this.category);
      stmt.setInt(4, board.getNo());
      stmt.setString(5, board.getPassword());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(Board board) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "delete from JavaProject_board"
            + " where category=? and board_no=? and password=sha1(?)")) {

      stmt.setInt(1, this.category);
      stmt.setInt(2, board.getNo());
      stmt.setString(3, board.getPassword());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
