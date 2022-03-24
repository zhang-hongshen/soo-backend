package com.hanson.soo.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 字段自动注入
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("插入注入");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        strictInsertFill(metaObject, "createTime", Timestamp.class, timestamp);
        strictInsertFill(metaObject, "updateTime", Timestamp.class, timestamp);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("更新注入");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        strictUpdateFill(metaObject, "updateTime", Timestamp.class, timestamp);
    }
}
