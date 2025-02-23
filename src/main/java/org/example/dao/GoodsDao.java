package org.example.dao;

import org.example.entity.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GoodsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostConstruct
    public void init() {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    public GoodsEntity getGoodsById(int id) {
        //String sql = "select * from goods where id = ?";
        //return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(GoodsEntity.class));

        String sql = "select * from goods where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(GoodsEntity.class));

    }

    public int updateGoodsById(GoodsEntity goods) {
        //String sql = "update goods set inventory = ? where id = ?";
        //return jdbcTemplate.update(sql, goods.getInventory(), goods.getId());

        String sql = "update goods set inventory = :inventory where id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("inventory", goods.getInventory());
        params.put("id", goods.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public int[] batchUpdate(List<GoodsEntity> goodsList) {
//        String sql = "update goods set inventory = ? where id = ?";
//        List<Object[]> batchArgs = new ArrayList<>();
//        for (GoodsEntity goods : goodsList) {
//            batchArgs.add(new Object[]{goods.getInventory(), goods.getId()});
//        }
//        return jdbcTemplate.batchUpdate(sql, batchArgs);

        String sql = "update goods set inventory = :inventory where id = :id";
        SqlParameterSource[] batchArgs = SqlParameterSourceUtils
                .createBatch(goodsList.toArray());
        return namedParameterJdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public int insertGoods(GoodsEntity goods) {
        String sql = "insert into goods (name, inventory) values (?, ?)";
        return jdbcTemplate.update(sql, new Object[]{goods.getName(), goods.getInventory()});
    }

    public int[] batchInsertGoods(List<GoodsEntity> goodsList) {
        String sql = "insert into goods (name, inventory) values (?, ?)";
        List<Object[]> paramList = new ArrayList<>();
        for (int i = 0; i < goodsList.size(); i++) {
            Object[] tmp = new Object[]{goodsList.get(i).getName(), goodsList.get(i).getInventory()};
            paramList.add(tmp);
        }
        return jdbcTemplate.batchUpdate(sql, paramList);
    }

    public int deleteGoods(Long id) {
        String sql = "delete from goods where id = ?";
        return jdbcTemplate.update(sql, id);
    }


}
