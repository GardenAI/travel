package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.favoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.inter.favoriteServiceInterface;

public class favoriteServiceImpl implements favoriteServiceInterface {
    favoriteDaoImpl fdao = new favoriteDaoImpl();
    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite = fdao.findFavorite(rid, uid);
        return favorite != null;
    }

    @Override
    public void addFavorite(int rid, int uid) {
        fdao.addFavorite(rid,uid);
    }
}
