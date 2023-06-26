package cn.itcast.travel.dao.inter;

import cn.itcast.travel.domain.Seller;

public interface SellerDaoInterface {
    Seller findbyId(int rid);
}
