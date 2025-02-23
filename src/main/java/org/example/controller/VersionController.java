package org.example.controller;

import com.google.gson.Gson;
import org.example.dao.GoodsDao;
import org.example.dao.GoodsMapper;
import org.example.entity.GoodsEntity;
import org.example.param.UpdateGoodsParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/version")
public class VersionController {
    private static final Logger logger = LoggerFactory.getLogger(VersionController.class);
    private static final Gson gson = new Gson();
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsMapper goodsMapper;

    @GetMapping("getVersion")
    public String getVersion() {
        logger.info("getVersion");
        //GoodsEntity goodsEntity = goodsDao.getGoodsById(1);
        GoodsEntity goodsEntity = goodsMapper.getGoodsById(1L);
        return gson.toJson(goodsEntity);
    }

    @PostMapping("updateInventory")
    public int updateInventory(@RequestBody UpdateGoodsParam param) {
        GoodsEntity entity = new GoodsEntity();
        BeanUtils.copyProperties(param, entity);
        return goodsDao.updateGoodsById(entity);
    }

    @PostMapping("batchUpdateInventory")
    public String batchUpdateInventory(@RequestBody UpdateGoodsParam param) {
        GoodsEntity entity = new GoodsEntity();
        BeanUtils.copyProperties(param, entity);
        List<GoodsEntity> goodsEntityList = new ArrayList<>();
        goodsEntityList.add(entity);
        return gson.toJson(goodsDao.batchUpdate(goodsEntityList));
    }

    @PostMapping("batchInsert")
    public String batchInsert(@RequestBody List<GoodsEntity> goodsEntityList) {
        return gson.toJson(goodsDao.batchInsertGoods(goodsEntityList));
    }

    @PostMapping("delete")
    public int delete(@RequestBody UpdateGoodsParam param) {
        return goodsDao.deleteGoods(param.getId());
    }
}
