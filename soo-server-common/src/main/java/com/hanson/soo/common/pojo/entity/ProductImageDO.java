package com.hanson.soo.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
@TableName("soo_product_image")
public class ProductImageDO {
    @TableId(type = IdType.AUTO)
    Long id;
    @TableField("product_id")
    String productId;
    @TableField("image_url")
    String url;
    @TableField("status")
    Boolean status;
    @TableField("create_time")
    Date createTime;
    @TableField("update_time")
    Date updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductImageDO that = (ProductImageDO) o;

        if (!productId.equals(that.productId)) return false;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
