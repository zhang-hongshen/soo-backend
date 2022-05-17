package com.hanson.soo.common.pojo;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductState {
    ALL(0, "全部"),
    ON_SALE(1, "售卖中"),
    OFF_SALE(2, "已下架");

    private static final BiMap<Integer, String> statusMap = HashBiMap.create();

    static {
        for (ProductState state : ProductState.values()) {
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
