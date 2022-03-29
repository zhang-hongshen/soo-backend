package com.hanson.soo.admin.service;


import com.hanson.soo.admin.pojo.dto.OrderInfoDTO;
import com.hanson.soo.common.pojo.dto.PageListDTO;

import java.util.List;

public interface OrderInfoService {
    PageListDTO<List<OrderInfoDTO>> listInfo(int current, int pageSize, OrderInfoDTO query);
}
