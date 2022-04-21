package com.hanson.soo.user.pojo;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    ALL(0, "全部"),
    ON_SALE(1, "售卖中"),
    OFF_SALE(2, "已下架");

    private static final BiMap<Integer, String> statusMap = HashBiMap.create();

    static {
        for (ProductStatusEnum status : ProductStatusEnum.values()) {
            statusMap.put(status.getStatus(), status.getValue());
        }
    }

    private final Integer status;
    private final String value;

    ProductStatusEnum(Integer status, String value) {
        this.status = status;
        this.value = value;
    }

    public static Integer getStatusByValue(String value) {
        return statusMap.inverse().get(value);
    }

    public static String getValueByStatus(Integer status) {
        return statusMap.get(status);
    }
}
