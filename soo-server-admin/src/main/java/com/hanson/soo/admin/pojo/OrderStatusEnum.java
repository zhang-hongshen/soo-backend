package com.hanson.soo.admin.pojo;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    ALL(0,"全部"),
    TO_BE_PAY(1, "待付款"),
    PAID(2, "已付款"),
    FINISHED(3, "已完成"),
    CLOSED(4, "已关闭"),
    REFUNDING(5, "退款中");

    private static final BiMap<Integer, String> statusMap = HashBiMap.create();

    static {
        for (OrderStatusEnum status : OrderStatusEnum.values()) {
            statusMap.put(status.getStatus(), status.getValue());
        }
    }

    private final Integer status;
    private final String value;

    OrderStatusEnum(Integer status, String value) {
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
