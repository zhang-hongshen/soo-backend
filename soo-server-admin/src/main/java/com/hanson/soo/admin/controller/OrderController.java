package com.hanson.soo.admin.controller;

import com.hanson.soo.admin.pojo.dto.OrderInfoDTO;
import com.hanson.soo.admin.pojo.qo.OrderQO;
import com.hanson.soo.admin.pojo.vo.OrderInfoVO;
import com.hanson.soo.admin.service.OrderService;
import com.hanson.soo.admin.utils.ConverterUtils;
import com.hanson.soo.common.pojo.dto.PageDTO;
import com.hanson.soo.common.pojo.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/info")
    public PageVO<List<OrderInfoVO>> query(@RequestParam("current")int current, @RequestParam("pageSize")int pageSize,
                                           @RequestBody OrderQO orderQO) {
        PageDTO<List<OrderInfoDTO>> pageDTO = orderService.listOrderInfos(current, pageSize,
                orderQO);
        List<OrderInfoVO> orderInfoVOs = pageDTO.getList().stream()
                .map(ConverterUtils::orderInfoDTO2VO).collect(Collectors.toList());
        return new PageVO<>(orderInfoVOs, pageDTO.getTotal());
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestBody List<String> orderIds) {
        return orderService.deleteByOrderIds(orderIds);
    }
}
