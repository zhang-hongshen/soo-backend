package com.hanson.soo.admin.controller;

import com.hanson.soo.admin.pojo.OrderStatusEnum;
import com.hanson.soo.admin.pojo.dto.OrderInfoDTO;
import com.hanson.soo.admin.pojo.qo.OrderInfoQO;
import com.hanson.soo.admin.pojo.vo.OrderInfoVO;
import com.hanson.soo.admin.service.OrderService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/info")
    public PageVO<List<OrderInfoVO>> query(@RequestParam("current")int current, @RequestParam("pageSize")int pageSize,
                                           @RequestBody OrderInfoQO orderInfoQO) {
        PageDTO<List<OrderInfoDTO>> pageDTO = orderService.listOrderInfos(current, pageSize,
                ConverterUtils.orderInfoQO2DTO(orderInfoQO));
        List<OrderInfoVO> orderInfoVOs = new ArrayList<>();
        for (OrderInfoDTO orderInfoDTO : pageDTO.getList()) {
            OrderInfoVO orderInfoVO = ConverterUtils.orderInfoDTO2VO(orderInfoDTO);
            orderInfoVO.setStatus(OrderStatusEnum.getValueByStatus(orderInfoDTO.getStatus()));
            orderInfoVOs.add(orderInfoVO);
        }
        return new PageVO<>(orderInfoVOs, pageDTO.getTotal());
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestBody List<String> orderIds) {
        return orderService.deleteByOrderIds(orderIds);
    }
}
