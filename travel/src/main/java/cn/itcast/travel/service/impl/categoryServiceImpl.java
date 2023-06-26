package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.categoryDaoImpl;
import cn.itcast.travel.dao.inter.categoryDaoInterface;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.inter.categoryServiceInterface;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class categoryServiceImpl implements categoryServiceInterface {
    categoryDaoImpl dao = new categoryDaoImpl();
    @Override
    public List<Category> findCategories() {
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs=null;
        if (category ==null || category.size() == 0){
            System.out.println("数据库......");
            cs = dao.findCategories();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        }else {
            System.out.println("redis.....");
            cs = new ArrayList<Category>();
            for(Tuple tuple : category){
                Category category1 = new Category();
                category1.setCname(tuple.getElement());
                category1.setCid((int)tuple.getScore());
                cs.add(category1);
            }
        }
        return cs;
    }
}
