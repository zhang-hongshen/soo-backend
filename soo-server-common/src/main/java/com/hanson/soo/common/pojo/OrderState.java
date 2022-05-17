package com.hanson.soo.common.pojo;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {
    ALL(0,"全部"),
    SUBMITTED(1, "待付款"),
    PAID(2, "已付款"),
    REFUNDING(3, "退款中"),
    CLOSED(4, "已关闭"),
    FULFILLED(5, "已完成");


    private static final BiMap<Integer, String> statusMap = HashBiMap.create();

    static {
        for (OrderState state : OrderState.values()) {
            statusMap.put(state.getState(), state.getValue());
        }
    }

    private final Integer state;
    private final String value;

    public static Integer getStateByValue(String value) {
        return statusMap.inverse().get(value);
    }
    public static String getValueByState(Integer state) {
        return statusMap.get(state);
    }
}