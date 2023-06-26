package cn.itcast.travel.dao.inter;

import cn.itcast.travel.domain.Favorite;

public interface favoriteDaoInterface {
    Favorite findFavorite(int rid,int uid);
    int favoriteCount(int rid);
    void addFavorite(int rid,int uid);
}
