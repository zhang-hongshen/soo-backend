package com.hanson.soo.admin.service;

import com.hanson.soo.admin.pojo.dto.OrderInfoDTO;
import com.hanson.soo.common.pojo.dto.PageDTO;

import java.util.List;

public interface OrderService {

    PageDTO<List<OrderInfoDTO>> listOrderInfos(int current, int pageSize, OrderInfoDTO query);

    boolean deleteByOrderIds(List<String> orderIds);
}
