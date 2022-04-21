package com.hanson.soo.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分页数据
 * @param <T>
 */
@Data
@AllArgsConstructor
public class PageDTO<T> {
    T list;
    int total;
}
