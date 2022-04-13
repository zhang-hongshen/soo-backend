package com.hanson.soo.common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PageVO<T> {
    T list;
    int total;
}
