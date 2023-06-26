package cn.itcast.travel.service.inter;

public interface favoriteServiceInterface {
    boolean isFavorite(int rid,int uid);
    void addFavorite(int rid,int uid);
}
