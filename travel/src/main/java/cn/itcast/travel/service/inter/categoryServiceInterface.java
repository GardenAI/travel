package cn.itcast.travel.service.inter;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface categoryServiceInterface {
    List<Category> findCategories();
}
