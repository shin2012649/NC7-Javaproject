package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.WatchList;

public interface WatchListDao {
    void insert(WatchList watchList);
    List<WatchList> findByUsersNo(int usersNo);
    List<WatchList> findByFilmsNo(int filmsNo);
    int delete(int usersNo, int filmsNo);
}
