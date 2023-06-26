package cn.itcast.travel.dao.inter;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface categoryDaoInterface {
    List<Category> findCategories();
}
