package org.example.service;

import com.google.gson.Gson;
import org.example.entity.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private static final Gson gson = new Gson();
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void test1() {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setId(1L);
        goodsEntity.setName("test");
        stringRedisTemplate.opsForValue().set("test", gson.toJson(goodsEntity));
        String str = stringRedisTemplate.opsForValue().get("test");
        GoodsEntity goodsEntity1 = gson.fromJson(str, GoodsEntity.class);
        System.out.println(goodsEntity1.getName());
    }

    public void testLock() {
        Boolean res = stringRedisTemplate.opsForValue().setIfAbsent("test:lock", "1", 60, TimeUnit.SECONDS);
        System.out.println("第一次加锁结果 = " + res);
        res = stringRedisTemplate.opsForValue().setIfAbsent("test:lock", "1", 60, TimeUnit.SECONDS);
        System.out.println("第二次加锁结果 = " + res);
    }
}
