package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@TableName("soo_product_image")
public class ProductImageDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("product_id")
    String productId;
    @TableField("image_url")
    String url;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    Timestamp createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    Timestamp updateTime;
    @TableField("is_deleted")
    Boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductImageDO that = (ProductImageDO) o;

        if (!id.equals(that.id)) return false;
        if (!productId.equals(that.productId)) return false;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + productId.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
