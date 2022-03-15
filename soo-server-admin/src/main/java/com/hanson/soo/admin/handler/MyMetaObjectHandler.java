package com.hanson.soo.admin.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.strictInsertFill(metaObject, "createTime", Timestamp.class, timestamp);
        this.strictInsertFill(metaObject, "updateTime", Timestamp.class, timestamp);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.strictUpdateFill(metaObject, "updateTime", Timestamp.class, timestamp);
    }
}
