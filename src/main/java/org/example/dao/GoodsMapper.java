package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.GoodsEntity;

@Mapper
public interface GoodsMapper {
    GoodsEntity getGoodsById(Long id);
}
